$(document).ready(function(){

		$('#registration-form').validate({
	    rules: {
	       firstname: {
	        required: true,
			minlength: 1
	      },
		  
		 lastname: {
	        minlength: 1
	       
	      },
		  
		  password: {
				required: true,
				minlength: 6
			},
			cnfpassword: {
				required: true,
				minlength: 6,
				equalTo: "#password"
			},
		  
	      email: {
	        required: true,
	        email: true
	      },

		  mobile: {
	        required: true,
			number: true
	      },

		bloodgroup: {
	        required: true
	      },
	     
		   address: {
	      	minlength: 10,
	        required: true
	      },
		  
		country: {
	        required: true
	      },
	
		state: {
	        required: true
	      },
	
		city: {
	        required: true
	      }
	    },
			highlight: function(element) {
				$(element).closest('.control-group').removeClass('success').addClass('error');
			},
			success: function(element) {
				element
				.text('').addClass('valid')
				.closest('.control-group').removeClass('error').addClass('success');
			},

		submitHandler: function(form) {
          alert('valid form');  // for demo
          $('form').find(":submit").attr("disabled", true).attr("value","Submitting...");
          alert("hie");
          // form.submit();  // for demo
         //return false;      // for demo


		



        }
	  });

}); // end document.ready