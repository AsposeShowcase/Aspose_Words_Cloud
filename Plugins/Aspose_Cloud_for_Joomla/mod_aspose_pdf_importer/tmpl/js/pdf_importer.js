jQuery(document).ready(function($){
    //alert(insert_pdf_url);

    jQuery('#submit_btn').live('click',function() {
        $("#aspose_pdf_popup_container").dialog('close');
        $body = $("body");
        $body.addClass("loading");

        jQuery('#my_file_form').ajaxSubmit({

            dataType: "html",
            success: function(response) {
                $body.removeClass("loading");
                tinyMCE.activeEditor.dom.add(tinyMCE.activeEditor.getBody(), 'p', {title: 'File Extracted Content'}, response);
                jQuery('#aspose_pdf_popup_container').dialog( "close" );
            }

        });
    });



    jQuery( "#aspose_pdf_popup_container" ).tabs();

    jQuery( "#aspose_pdf_popup_container" ).dialog({
        autoOpen: false,
        resizable: false,
        height: 450,
        width: 600,
        draggable: false,
        modal: true,
        title: 'PDF Importer',
        open: function() {
            jQuery('.ui-widget-overlay').bind('click', function() {
                jQuery('#aspose_pdf_popup_container').dialog('close');
            })
        }
    });

    $('#insert_aspose_pdf_content').live('click',function(){
        var filename = $('input[name="aspose_filename"]:checked').val();
        $("#aspose_pdf_popup_container").dialog('close');
        $body = $("body");
        $body.addClass("loading");

        jQuery.ajax
        ({
            type : "post",
            dataType : "html",
            url : insert_pdf_url,
            data : {filename : filename, uploadpath: uploadpath , aspose : '1'},
            success: function(response) {

                $body.removeClass("loading");
                tinyMCE.activeEditor.dom.add(tinyMCE.activeEditor.getBody(), 'p', {title: 'File Extracted Content'}, response);
                jQuery('#aspose_pdf_popup_container').dialog( "close" );

            }
        });

    });

});

function load_uploader() {
    jQuery('#aspose_pdf_popup_container').dialog( "open" );
    //jQuery('.ui-dialog-titlebar').remove();
}


