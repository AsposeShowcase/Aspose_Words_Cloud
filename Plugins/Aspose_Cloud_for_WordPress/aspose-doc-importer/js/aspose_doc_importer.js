jQuery(document).ready(function($){

    jQuery.ajax
    ({
        type : "post",
        dataType : "html",
        url : AsposeDocParams['aspose_files_url'],
        data : {appSID: AsposeDocParams['appSID'], appKey : AsposeDocParams['appKey']},
        success: function(response) {
            $('#aspose_cloud_doc').append(response);

        }
    });

    $('#aspose_folder_name').live('change',function() {
        var selected_folder_name = $(this).val();
        if(selected_folder_name != '') {
            jQuery.ajax
            ({
                type : "post",
                dataType : "html",
                url : AsposeDocParams['aspose_files_url'],
                data : {appSID: AsposeDocParams['appSID'], appKey : AsposeDocParams['appKey'], aspose_folder : selected_folder_name},
                success: function(response) {
                    $('#aspose_cloud_doc').html(response);

                }
            });
        }
    });



    $('#tabs').tabs();
    $('#aspose_doc_popup').live("click",function(){
        $("#aspose_doc_popup_container").dialog('open');

    });
    $("#aspose_doc_popup_container").dialog({
        autoOpen: false,
        resizable: false,
        modal: true,
        width:'auto',
        height:'300',
    });


    $('#insert_doc_content').live('click',function(){

        var filename = $('#doc_file_name').val();
        $("#aspose_doc_popup_container").dialog('close');
        $body = $("body");
        $body.addClass("loading");

        jQuery.ajax
        ({
            type : "post",
            dataType : "html",
            url : AsposeDocParams['insert_doc_url'],
            data : {appSID: AsposeDocParams['appSID'], appKey : AsposeDocParams['appKey'], filename : filename, uploadpath: AsposeDocParams['uploadpath'] , uploadURI: AsposeDocParams['uploadURI']},
            success: function(response) {
                $body.removeClass("loading");

                window.send_to_editor(response);
            }
        });
    });

    $('#insert_aspose_doc_content').live('click',function(){
        var filename = $('input[name="aspose_filename"]:checked').val();
        $("#aspose_doc_popup_container").dialog('close');
        $body = $("body");
        $body.addClass("loading");

        jQuery.ajax
        ({
            type : "post",
            dataType : "html",
            url : AsposeDocParams['insert_doc_url'],
            data : {appSID: AsposeDocParams['appSID'], appKey : AsposeDocParams['appKey'], filename : filename, uploadpath: AsposeDocParams['uploadpath'] , uploadURI: AsposeDocParams['uploadURI'] , aspose : '1'},
            success: function(response) {
                $body.removeClass("loading");
                window.send_to_editor(response);
            }
        });

    });


});



