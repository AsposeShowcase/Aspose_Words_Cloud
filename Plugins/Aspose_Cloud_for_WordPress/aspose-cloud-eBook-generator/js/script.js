jQuery(function() {
    jQuery('input[name="ape-allposts-export_from"]').datepicker({
        //defaultDate: "+1w",
        changeMonth: true,
        numberOfMonths: 1,
        onClose: function( selectedDate ) {
            jQuery('input[name="ape-allposts-export_to"]').datepicker( "option", "minDate", selectedDate );
        }
    });
    jQuery('input[name="ape-allposts-export_to"]').datepicker({
        //defaultDate: "+1w",
        changeMonth: true,
        numberOfMonths: 1,
        onClose: function( selectedDate ) {
            jQuery('input[name="ape-allposts-export_from"]').datepicker( "option", "maxDate", selectedDate );
        }
    });

    // Temporary, to remove datepicker divs that break design
    jQuery('#ui-datepicker-div').remove();
});