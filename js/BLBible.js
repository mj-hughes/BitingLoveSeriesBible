$(function(){
	$('#editCharacter').on('click', editCharacter);

	$('#saveCharacter').on('click', saveCharacter);
});

// Edit a character
function editCharacter() {
//		window.location = 'itemEditDetailNixie.html'; from oroborous worldbible https://github.com/oroborous/worldbible/blob/master/characterView.html
	$(location).attr('href', 'itemEditDetailNixie.html');
}

// Save character edits
function saveCharacter() {
	// Blank message 
	$('#saveMessage').html('');
	
	// Error variables
	var errors=false;
	var errMsg = "";
	
	// Validate age, if any
	var age=$('#age').val();

	// This blanks extraneous non-numeric characters like ee and 10++
	$('#age').val(age);
	
	if ((age<-100) || (age>15000)) {
		errors=true;
		errMsg+='Age must be between -100 and 15000; ';
	}
	
	// Let user know if errors or saved
	if (errors) {
		$('#saveMessage').html(errMsg);
	}
	else {
		$('#saveMessage').html('Saved!');		
	}
}
