(function($) {
	'use strict';
		jQuery(document).on('ready', function(){
			/*PRELOADER JS*/
			$(window).on('load', function() { 
				$('.loader').fadeOut();
				$('.preloader').delay(350).fadeOut('slow'); 
			}); 
			/*END PRELOADER JS*/
				
			// jQuery Lightbox
			jQuery('.venobox').venobox({
				numeratio: true,
				infinigall: true
			});	
			
			$(window).scroll(function() {
				
				  if ($(this).scrollTop() > 100) {
					$('#header').addClass('menu-shrink');
				  } else {
					$('#header').removeClass('menu-shrink');
				  }
				});
				
				$('a').on('click', function(e){
					var anchor = $(this);
					$('html, body').stop().animate({
						scrollTop: $(anchor.attr('href')).offset().top - 50
					}, 1500);
					e.preventDefault();
				});
					
				$(document).on('click','.navbar-collapse.in',function(e) {
				if( $(e.target).is('a') && $(e.target).attr('class') != 'dropdown-toggle' ) {
					$(this).collapse('hide');
				}
				});		
					
			new WOW().init();
			
			// Main slider
			$('.slider_active').owlCarousel({
				loop:true,
				navText:['<i class="fa fa-angle-left"></i>','<i class="fa fa-angle-right"></i>'],
				animateIn: 'fadeIn',
				animateOut: 'fadeOut',
				smartSpeed:450,
				autoplay:true,
				autoplayTimeout:6000,
				mouseDrag:false,
				nav:true,
				responsive:{
					0:{
						items:1
					},
					600:{
						items:1
					},
					1000:{
						items:1
					}
				}
			})	
			


			// blog slider
			$('#blog_slider').owlCarousel({
				autoplay:true,
				items: 3,
				margin: 30,
				loop:true,
				nav:false,
				navText:['<i class="fa fa-angle-left"></i>','<i class="fa fa-angle-right"></i>'],
				responsive:{
					0:{
						items:1
					},
					600:{
						items:2
					},
					1000:{
						items:3
					}
				}
			})


			// testimonial slider
			$('#testimonial-slider').owlCarousel({
			autoplay:true,
			items: 2,
			margin: 30,
			autoplayTimeout:2000,
			mouseDrag:true,
			loop:true,
			responsive:{
				0:{
					items:1
				},
				600:{
					items:2
				},
				1000:{
					items:2
				}
			}
			})
			
			
			
			// client slider 
			$('.client_slide_area').owlCarousel({
			autoplay:true,
			margin: 50,
			loop:true,
			responsive:{
				0:{
					items:1
				},
				600:{
					items:3
				},
				1000:{
					items:5
				}
			}
			})
			
			
			// Counter 
			$('.counter').counterUp({
				delay: 10,
				time: 1000
			});


			// jQuery MixItUp
			$('.portfolio_item').mixItUp();
			
			 // Vide Section
			$("#video").simplePlayer();
			
		});
		
	/*  Stellar for background scrolling  */
	(function () {

		if( /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) {
		 
		} else {
			$(window).stellar({
				horizontalScrolling: false,
				responsive: true
			});
		}

	}());
	/* End Stellar for background scrolling  */		
		
		// Active  WOW
		new WOW().init();

})(jQuery);	
