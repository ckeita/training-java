$(function() {
	$.fn.datepicker.defaults.language = 'fr';
	$.fn.datepicker.dates.fr.format = 'yyyy-mm-dd';
	$('#introduced').datepicker();
	$('#discontinued').datepicker();
	var int_date = $('#introduced').val();
	$("#form_id").bootstrapValidator({
		feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	computerName: {
                validators: {
                    notEmpty: {
                        message: 'The name is required and cannot be empty'
                    }
                }
            },
            introduced: {
                validators: {
                  date: {
                    format: 'yyyy-mm-dd',
                    max: 'discontinued',
                    message: 'The date must be lower than the above date'
                  }
            	}
        	},
            discontinued: {
                validators: {
                  date: {
                    format: 'yyyy-mm-dd',
                    min: 'introduced',
                    message: 'The date must be greater than the above date'
                  }
            	}
        	}
        }
	});
	$('#introduced_date').on('changeDate show', function(e) {
        $('#form_id').bootstrapValidator('revalidateField', 'introduced');
    });
	$('#discontinued_date').on('changeDate show', function(e) {
        $('#form_id').bootstrapValidator('revalidateField', 'discontinued');
    });
});