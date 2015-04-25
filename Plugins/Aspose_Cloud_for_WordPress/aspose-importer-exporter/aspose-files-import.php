<?php


/**
 * Add the javascript for the plugin
 * @param no-param
 * @return string
 */
function AsposePdfImporterEnqueueScripts() {

    wp_register_script( 'aspose_pdf_importer_script', plugins_url( 'js/aspose_pdf_importer.js', __FILE__ ), array('jquery') );

    $upload_path = wp_upload_dir();
    $params = array(
        'appSID'            => get_option('aspose_import_export_app_sid'),
        'appKey'            => get_option('aspose_import_export_app_key'),
        'uploadpath'        => $upload_path['path'],
        'insert_pdf_url'    => plugins_url( 'getAsposeFileContent.php', __FILE__ ),
        'aspose_files_url'    => plugins_url( 'getAsposeFiles.php', __FILE__ ),

    );

    wp_localize_script( 'aspose_pdf_importer_script', 'AsposeParams', $params );

    wp_enqueue_script( 'jquery-ui-dialog' );
    wp_enqueue_script( 'jquery-ui-tabs' );
    wp_enqueue_script( 'aspose_pdf_importer_script' );

    wp_register_style( 'AsposePdfImporterStyle', plugins_url( 'css/style.css', __FILE__), array(), '' );

    wp_enqueue_style( 'AsposePdfImporterStyle');
    wp_enqueue_style( 'jquery-ui-tabs');
    wp_enqueue_style( 'wp-jquery-ui-dialog');



}

add_action('init', 'AsposePdfImporterEnqueueScripts');

// For adding button for Aspose Cloud Pdf Importer
add_action('media_buttons_context',  'add_aspose_pdf_importer_button');

function add_aspose_pdf_importer_button($context){
    //path to my icon

    $context .= '<a id="aspose_pdf_popup" title = "Aspose File Importer" class="button-primary">Aspose File Importer</a>';

    return $context;
}

add_action( 'admin_footer',  'aspose_pdf_add_inline_popup_content' );
function aspose_pdf_add_inline_popup_content() {
    ?>
	<style type="text/css">
        .ui-widget-overlay {
            z-index:100 !important;
        }
    </style>
    <div id="aspose_pdf_popup_container" title="Aspose File Importer">
        <p>
            <?php
            if( get_option('aspose_import_export_app_sid') == '' || get_option('aspose_import_export_app_key') == '') { ?>
        <h3 style="color:red"> Please go to settings page and enter valid Aspose Cloud App ID & Key. </h3>
        <?php
        } else { ?>
            <div id="tabs">
                <ul>
                    <li><a href="#tabs-1">From Local</a></li>
                    <li><a href="#tabs-2">From Aspose Cloud Storage</a></li>
                </ul>
                <div id="tabs-1">
                    <table>
                        <tr>
                            <td>
                                <?php
                                $image_library_url = get_upload_iframe_src( );
                                $image_library_url = remove_query_arg( array('TB_iframe'), $image_library_url );
                                $image_library_url = add_query_arg( array( 'context' => 'Aspose-Pdf-Importer-Select-File', 'TB_iframe' => 1 ), $image_library_url );
                                ?>

                                <p>
                                        <a title="Select File" href="<?php echo esc_url( $image_library_url ); ?>" id="select-pdf-file" class="button thickbox">Select File</a>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td><input type="text" name="pdf_file_name" style="width:250px; margin-right:10px;" id="pdf_file_name" readonly value="" />  </td>
                            <td style="margin-left:10px; vertical-align: top;"> <input type="button" class="button-primary" id="insert_pdf_content" value="Insert File to Editor" /> </td>
                        </tr>


                    </table>
                </div>
                <div id="tabs-2">
                    <input type="button" class="button-primary" style="position:fixed; margin-top:5px; margin-left:350px;" id="insert_aspose_pdf_content" value="Insert File to Editor" />
                    <table id="aspose_cloud_pdf" style="height: 250px; width:500px !important; overflow-y: scroll;">

                    </table>


                </div>
                <div id="target"></div>
            </div>
        <?php
        } ?>
        </p>
    </div>

    <div class="modal"></div>

<?php
}

add_filter('upload_mimes', 'aspose_pdf_importer_upload_mimes');

if (check_upload_aspose_pdf_context('Aspose-Pdf-Importer-Select-File')) {

    add_filter('media_upload_tabs', 'aspose_pdf_importer_image_tabs', 10, 1);
    add_filter('attachment_fields_to_edit', 'aspose_pdf_importer_action_button', 20, 2);
    add_filter('media_send_to_editor', 'aspose_pdf_importer_file_selected', 10, 3);
}

function aspose_pdf_importer_image_tabs($_default_tabs) {

    unset($_default_tabs['type_url']);
    return($_default_tabs);
}

function aspose_pdf_importer_upload_mimes ( $existing_mimes=array() ) {


    $existing_mimes = array();
    $existing_mimes['pdf'] = 'application/pdf';
    $existing_mimes['doc'] = 'application/msword';
    $existing_mimes['docx'] = 'application/vnd.openxmlformats-officedocument.wordprocessingml.document';
    $existing_mimes['xlsx'] = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';
    $existing_mimes['xla|xls|xlt|xlw'] = 'application/vnd.ms-excel';


    return $existing_mimes;
}

function aspose_pdf_importer_action_button($form_fields, $post) {

    $send = "<input type='submit' class='button-primary' name='send[$post->ID]' value='" . esc_attr__( 'Use this Pdf File For Importing' ) . "' />";

    $form_fields['buttons'] = array('tr' => "\t\t<tr class='submit'><td></td><td class='savesend'>$send</td></tr>\n");
    $form_fields['context'] = array( 'input' => 'hidden', 'value' => 'Aspose-Pdf-Importer-Select-File' );
    return $form_fields;
}


function aspose_pdf_importer_file_selected($html, $send_id) {

    $file_url = wp_get_attachment_url($send_id);
    $file_url = basename($file_url);
    ?>
    <script type="text/javascript">
        /* <![CDATA[ */
        var win = window.dialogArguments || opener || parent || top;

        win.jQuery( '#pdf_file_name' ).val('<?php echo $file_url;?>');

    </script>
    <?php
    return '';
}

function add_aspose_pdf_context_to_url($url, $type) {
    //if ($type != 'image') return $url;
    if (isset($_REQUEST['context'])) {
        $url = add_query_arg('context', $_REQUEST['context'], $url);
    }
    return $url;
}


function check_upload_aspose_pdf_context($context) {
    if (isset($_REQUEST['context']) && $_REQUEST['context'] == $context) {
        add_filter('media_upload_form_url', 'add_aspose_pdf_context_to_url', 10, 2);
        return TRUE;
    }
    return FALSE;
}
