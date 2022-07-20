function admitDigitsOnly(input) {
	$(input).keypress(function(e) {
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			return false;
		}
	});
}

function admitDecimals(input) {
	$(input).keypress(function(e) {
		if (e.which == 46) {
			return true;
		}
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			return false;
		}
	});
}

function negarEnter(input) {
	$(input).keypress(function(e) {
		if (e.which == 13) {
			return false;
		}		
	});
}