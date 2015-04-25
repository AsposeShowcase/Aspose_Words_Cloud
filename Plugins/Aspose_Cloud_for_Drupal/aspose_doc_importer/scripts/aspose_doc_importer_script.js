(function ($) {
    Drupal.behaviors.my_module = {
        attach: function(context, settings) {
            // Use context to ensure the link is only ever activated if it's regenerated.

            // This code executes when a new file is selected to upload and convert
            jQuery('#submit_btn', context).click(function(event) {
                var $mySpecialLink = jQuery(this);
                // Only run if the link exists in the current page load or fragment refresh.
                if ($mySpecialLink.size() > 0) {
                    jQuery('#aspose_pdf_popup_container').dialog('close');
                    $body = jQuery("body");
                    $body.addClass("loading");

                    $.ajaxFileUpload ({
                        url: $mySpecialLink.attr('data-submit-to'),
                        secureuri: false,
                        fileElementId: 'pdf_file_selected',
                        dataType: 'json',
                        success: function (data, status) {
                            if(!data.success) {
                                if(data.message != '') {
                                    alert(data.message);
                                    $body.removeClass("loading");
                                }
                            } else {
                                jQuery('textarea[name="body[und][0][value]"]').val(data.file_content);
                                $body.removeClass("loading");
                            }
                        }
                    });
                }
            });

            // If an existing file on cloud is selected to convert, send this request
            jQuery('#insert_aspose_pdf_content', context).click(function(event){
                var $mySpecialLink = jQuery(this);
                // Only run if the link exists in the current page load or fragment refresh.
                if ($mySpecialLink.size() > 0) {
                    if(jQuery('input[name=aspose_filename]').length > 0) {
                        jQuery("#aspose_pdf_popup_container").dialog('close');
                        $body = jQuery("body");
                        $body.addClass("loading");

                        jQuery.ajax({
                            url: $mySpecialLink.attr('data-submit-to'),
                            dataType: 'json',
                            type: 'POST',
                            data: 'existing_file=' + jQuery('input[name=aspose_filename]').val(),
                            success: function (data) {
                                if(data.success) {
                                    jQuery('textarea[name="body[und][0][value]"]').val(data.file_content);
                                    $body.removeClass("loading");
                                }
                            }
                        });
                    } else {
                        jQuery('#no_file_selected_error').remove();
                        jQuery('#cloud_files').prepend('<span id="no_file_selected_error" style="color: red;">Please select a file</span>');
                    }
                }
            });
        }
    }
})(jQuery);

// Initializing Dialogs on document ready
jQuery(function() {
    jQuery("#aspose_pdf_popup_container").tabs();
    jQuery('#aspose_pdf_popup_container').dialog({
        autoOpen: false,
        resizable: false,
        height: 450,
        width: 600,
        draggable: false,
        modal: true,
        title: 'Aspose DOC Importer',
        open: function() {
            jQuery('.ui-widget-overlay').bind('click', function() {
                jQuery('#aspose_pdf_popup_container').dialog('close');
            });
        }
    });
});

// Show dialog to upload/select file
function load_uploader() {
    jQuery('#aspose_pdf_popup_container').dialog("open");
}