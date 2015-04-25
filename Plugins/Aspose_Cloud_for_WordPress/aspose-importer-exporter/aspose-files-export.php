<?php

add_action( 'admin_footer',  'aspose_import_export_add_export_option' );
function aspose_import_export_add_export_option() {
    ?>
    <script type="text/javascript">
        jQuery(document).ready(function($) {
            jQuery('<option>').val('aspose_export_pdf').text('<?php echo('Aspose Export To PDF')?>').appendTo("select[name='action']");
            jQuery('<option>').val('aspose_export_pdf').text('<?php echo('Aspose Export To PDF')?>').appendTo("select[name='action2']");
            jQuery('<option>').val('aspose_export_doc').text('<?php echo('Aspose Export To DOC')?>').appendTo("select[name='action']");
            jQuery('<option>').val('aspose_export_doc').text('<?php echo('Aspose Export To DOC')?>').appendTo("select[name='action2']");

        });
    </script>

<?php
}



add_action('load-edit.php','aspose_import_export_bulk_action');

function aspose_import_export_bulk_action(){

    $upload_dir = wp_upload_dir();
    $upload_path = $upload_dir['path'] . '/';

    $html_file = $upload_path . 'outpuut.html';
    $pdf_file = $upload_path . 'outpuut.pdf';
    $doc_file = $upload_path . 'outpuut.doc';

    @unlink($html_file);
    @unlink($pdf_file);
    @unlink($doc_file);

    global $typenow;
    $post_type = $typenow;

    // get the action
    $wp_list_table = _get_list_table('WP_Posts_List_Table');  // depending on your resource type this could be WP_Users_List_Table, WP_Comments_List_Table, etc
    $action = $wp_list_table->current_action();
    //echo $action; exit;

    $allowed_actions = array("aspose_export_pdf","aspose_export_doc");

    if(!in_array($action, $allowed_actions)) return;

    // security check
    check_admin_referer('bulk-posts');

    // make sure ids are submitted.  depending on the resource type, this may be 'media' or 'ids'
    if(isset($_REQUEST['post'])) {
        $post_ids = array_map('intval', $_REQUEST['post']);
    }

    if(empty($post_ids)) return;

    // this is based on wp-admin/edit.php
    $sendback = remove_query_arg( array('exported', 'untrashed', 'deleted', 'ids'), wp_get_referer() );

    if ( ! $sendback )
        $sendback = admin_url( "edit.php?post_type=$post_type" );

    $pagenum = $wp_list_table->get_pagenum();

    $sendback = add_query_arg( 'paged', $pagenum, $sendback );
    
    switch($action) {
        case 'aspose_export_pdf':

            $exported = count($post_ids);

            $post_contents = aspose_import_export_array_builder($post_ids);

            if ( is_array($post_contents) && count($post_contents) > 0 ) {
                $file_name = aspose_import_export_array_to_html($post_contents);
                include_once('asposePdfConverter.php');
            } else {
                wp_die( __('Error exporting post.') );
            }



            $sendback = add_query_arg( array('exported' => $exported, 'format' => 'Pdf' ), $sendback );

            break;

        case 'aspose_export_doc':

            $exported = count($post_ids);

            $post_contents = aspose_import_export_array_builder($post_ids);

            if ( is_array($post_contents) && count($post_contents) > 0 ) {
                $file_name = aspose_import_export_array_to_html($post_contents);
                include_once('asposeDocConverter.php');
            } else {
                wp_die( __('Error exporting post.') );
            }



            $sendback = add_query_arg( array('exported' => $exported, 'format' => 'Doc' ), $sendback );

            break;
        default:
            return;
    }



    $sendback = remove_query_arg( array('action', 'action2', 'tags_input', 'post_author', 'comment_status', 'ping_status', '_status',  'post', 'bulk_edit', 'post_view'), $sendback );

    wp_redirect($sendback);
    exit();
}

add_action('admin_notices','aspose_import_export_admin_notices');

function aspose_import_export_admin_notices() {

    if(isset($_REQUEST['format'])) {
        $format = $_REQUEST['format'];
    } else {
        $format = 'Pdf';
    }

    $upload_dir = wp_upload_dir();
    $upload_path = $upload_dir['path'] . '/';

    $file_name = $upload_path . 'output.'.$format;

    $download_path = plugin_dir_url(__FILE__) . 'aspose_import_export_download.php';
    global $post_type, $pagenow;

    if($pagenow == 'edit.php' && isset($_REQUEST['exported']) && (int) $_REQUEST['exported']) {
        $message = sprintf( _n( 'Post exported.', '%s posts exported.', $_REQUEST['exported'] ), number_format_i18n( $_REQUEST['exported'] ) );
        $message .='<a href="'.$download_path.'?file='.$file_name.'"> Click Here </a> to download the '.$format.' file.';
        echo "<div class=\"updated\"><p>{$message}</p></div>";
    }
}

