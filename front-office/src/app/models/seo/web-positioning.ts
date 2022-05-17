export interface WebPositioning {
  pagesTypes: PagesTypes ;
  robotsTags: RobotsTags [];
  metaTagsTrans: {id: number,
    langCodeId: number,
    langCode: string,
    title: string,
    description: string,
  }[];
}

export type PagesTypes  = 'HOME' |
'ABOUT_US '|
'CONTACT'|
'PRODUCT'|
'HOME'|
'CATEGORY'|
'COOKIES'|
'NWES'|
'REVENDEURS'|
'ASSISTANCE'|
'LINKS';


export type RobotsTags = 'index' |
  'noindex' |
  'none' |
  'follow' |
  'nofollow' |
  'noarchive' |
  'nosnippet' |
  'noodp' |
  'noydir' |
  'noimageindex';
