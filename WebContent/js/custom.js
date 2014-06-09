
$(document).on('ready', function(){
	
	$('#bid-form').ajaxform();

	var i = window.setInterval(function(){
		var float = parseFloat($('#time').html());
		if(float > 0){
			float--;
		$('#time').html(float);
		$('#hidden-time').val(float);
		}
		else{
			window.clearInterval(i);
			$('#submit').prop('disabled', true);
		}
	}, 1000);
	
});

; //create closure
(function($){
	//plugin definition
	$.fn.ajaxform = function(options){
		var $form = $(this);
		$.fn.ajaxform.defaults = 
		{
			action: $form.attr('action') || document.URL,
			method: $form.attr('method') || "GET",
			success: function(data){
				console.log('complete'); 
				if(data["money"] == undefined ){ 
					$('#error').html("$"+data["error"] + " is too low.");
				}
				else{
				$('#current-bid').html(data["money"] );
				$('#error').html("");
				}
				
				
				 },
			data: {}
		};
		var settings = $.extend({}, $.fn.ajaxform.defaults, options);
		
		$form.on('submit', function(event){
			$form.find('input').each(function(index){

				var $child = $(this);
				var name = $child.attr('name');
				if(!$child.is('input[type="submit"],input[type="button"]') && name != undefined || settings.data == []){
					settings.data[name] = $child.val();
				}
			});

			$.ajax({url:settings.action, type: settings.method,success:settings.success, data:settings.data });
			event.preventDefault();
		});			
	};
	//end closure
})(jQuery);