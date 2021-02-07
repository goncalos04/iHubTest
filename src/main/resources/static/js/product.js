$(document).ready(function(){
  $('#productForm').validate({
		rules: {
			"name": {
				required: true
			},
			"price": {
				required: true,
				digits: true
			}
		}
		
	});
	
	$('#productForm').submit(function(){
		if(!$('#productForm').valid()){
			return false;
		}
	});
});