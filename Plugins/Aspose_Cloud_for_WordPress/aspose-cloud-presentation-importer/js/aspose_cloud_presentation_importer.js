jQuery(document).ready(function($){

    jQuery.ajax
    ({
        type : "post",
        dataType : "html",
        url : AsposeParams['aspose_files_url'],
        data : {appSID: AsposeParams['appSID'], appKey : AsposeParams['appKey']},
        success: function(response) {
            $('#aspose_cloud_ppt').append(response);

        }
    });

    $('#aspose_folder_name').live('change',function() {
        var selected_folder_name = $(this).val();
        if(selected_folder_name != '') {
            jQuery.ajax
            ({
                type : "post",
                dataType : "html",
                url : AsposeParams['aspose_files_url'],
                data : {appSID: AsposeParams['appSID'], appKey : AsposeParams['appKey'], aspose_folder : selected_folder_name},
                success: function(response) {
                    $('#aspose_cloud_ppt').html(response);

                }
            });
        }
    });



    $('#tabs').tabs();
    $('#aspose_ppt_popup').live("click",function(){
        $("#aspose_ppt_popup_container").dialog('open');

    });
    $("#aspose_ppt_popup_container").dialog({
        autoOpen: false,
        resizable: false,
        modal: true,
        width:'auto',
        height:'300',
    });


    $('#insert_ppt_content').live('click',function(){
        var filename = $('#ppt_file_name').val();
        $("#aspose_ppt_popup_container").dialog('close');
        $body = $("body");
        $body.addClass("loading");

        jQuery.ajax
        ({
            type : "post",
            dataType : "html",
            url : AsposeParams['insert_ppt_url'],
            data : {appSID: AsposeParams['appSID'], appKey : AsposeParams['appKey'], filename : filename, uploadpath: AsposeParams['uploadpath']},
            success: function(response) {
                $body.removeClass("loading");

                window.send_to_editor(response);
            }
        });
    });

    $('#insert_aspose_ppt_content').live('click',function(){
        var filename = $('input[name="aspose_filename"]:checked').val();
        $("#aspose_ppt_popup_container").dialog('close');
        $body = $("body");
        $body.addClass("loading");

        jQuery.ajax
        ({
            type : "post",
            dataType : "html",
            url : AsposeParams['insert_ppt_url'],
            data : {appSID: AsposeParams['appSID'], appKey : AsposeParams['appKey'], filename : filename, uploadpath: AsposeParams['uploadpath'] , aspose : '1'},
            success: function(response) {
                $body.removeClass("loading");
                window.send_to_editor(response);
            }
        });

    });


});



