import { Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { BreadcrumbService } from '../_services/breadcrumb.service';
import { WebPositioningService } from '../_services/web-positioning.service';

@Component({
  selector: 'app-web-positioning',
  templateUrl: './web-positioning.component.html',
  styleUrls: ['./web-positioning.component.css']
})

export class WebPositioningComponent implements OnInit, OnDestroy {
  robots = [
    'index',
    'noindex',
    'none',
    'follow',
    'nofollow',
    'noarchive',
    'nosnippet',
    'noodp',
    'noydir',
    'noimageindex'
  ];
  webPositioningForm !:FormGroup ;
  page: any;
  selectedTab!: number;
  unsub$ = new Subject();
  langs: any;

  constructor(
    private formBuilder: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private webPositioningService: WebPositioningService,
    private breadcrumbService: BreadcrumbService,
    private matSnackbar: MatSnackBar
  ) {
  }

  ngOnDestroy(): void {
    this.unsub$.next();
    this.unsub$.complete();
  }

  ngOnInit() {
    this.activatedRoute.data
      .pipe(takeUntil(this.unsub$))
      .subscribe(({langs}) => {
        this.langs = langs;
      });
    this.page = this.activatedRoute.snapshot.paramMap.get('page');
    this.webPositioningForm = this.formBuilder.group({
      page: this.page.toUpperCase(),
      metaTagsTrans: this.formBuilder.array([]),
      robots: null,
    });
    this.activatedRoute.paramMap.pipe(takeUntil(this.unsub$))
      .subscribe(v => {
        this.page = v.get('page');
        this.webPositioningForm.reset();
        this.webPositioningForm.get('page')?.setValue(this.page);
        let pageName = this.webPositioningForm.get('page')?.value;
        pageName = pageName.toUpperCase();
        console.log(pageName);
        this.formSetup();
        this.breadcrumbService.sendBreadcrumb(['WEB_POSITIONING', pageName]);

      });

  }
  

  get metaTagsTrans() {
    return this.webPositioningForm.get('metaTagsTrans') as FormArray;
  }

  save() {
    this.webPositioningService.update(this.webPositioningForm.value).subscribe(() => {
      this.matSnackbar.open('UPDATED  SUCCESS' + '⚡', 'Ok', {duration: 1500});
    });
  }

  private formSetup() {
    if (this.metaTagsTrans) {
      while (this.metaTagsTrans.length > 0) {
        this.metaTagsTrans.removeAt(0);
      }
    }
    this.webPositioningService
      .getCurrentEnterprisePageInfo(this.page)
      .subscribe(info => {
        if (!info) {
          this.langs.forEach((u: { id: any; code: any; }) => {
            let ctrl = this.formBuilder.group({
              id: null,
              langCodeId: u.id,
              langCode: u.code,
              title: null,
              description: null
            });
            this.metaTagsTrans.push(ctrl);
          });
        } else {
          this.webPositioningForm.patchValue(info);
          this.langs.forEach((u: { id: number; code: any; }) => {
            const obj = info.metaTagsTrans.find(v => v.langCodeId === u.id);
            let ctrl = this.formBuilder.group({
              id: obj ? obj.id : null,
              langCodeId: u.id,
              langCode: u.code,
              title: obj ? obj.title : null,
              description: obj ? obj.description : null
            });
            this.metaTagsTrans.push(ctrl);
          });
        }
        
      });
  }

  @HostListener('window:keydown', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    if ((event.key === 's' || event.key === 'S') && event.altKey) {
      this.save();
    }
  }
}
