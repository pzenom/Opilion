$(document).ready(function () {
	$('#start').click(function() {
		$('#pass').val(hex_md5($('#pass').val()));
	});
});