<?php

/**
 * Create the admin menu for this plugin
 * @param no-param
 * @return no-return
 */
function AsposeDocExporterAdminMenu() {
    add_options_page('Aspose Doc Exporter', __('Aspose Doc Exporter', 'aspose-doc-exporter'), 'activate_plugins', 'AsposeDocExporterAdminMenu', 'AsposeDocExporterAdminContent');
}

add_action('admin_menu', 'AsposeDocExporterAdminMenu');

/**
 * Pluing settings page
 * @param no-param
 * @return no-return
 */
function AsposeDocExporterAdminContent() {

    // Creating the admin configuration interface
    ?>
    <div class="wrap">
    <h2><?php echo __('Aspose Doc Exporter Options', 'aspose-doc-exporter');?></h2>
    <br class="clear" />

    <div class="metabox-holder has-right-sidebar" id="poststuff">
    <div class="inner-sidebar" id="side-info-column">
        <div class="meta-box-sortables ui-sortable" id="side-sortables">
            <div id="AsposeDocExporterOptions" class="postbox">
                <div title="Click to toggle" class="handlediv"><br /></div>
                <h3 class="hndle"><?php echo __('Support / Manual', 'aspose-doc-exporter'); ?></h3>
                <div class="inside">
                    <p style="margin:15px 0px;"><?php echo __('For any suggestion / query / issue / requirement, please feel free to drop an email to', 'aspose-doc-exporter'); ?> <a href="mailto:marketplace@aspose.com?subject=Aspose Doc Exporter">marketplace@aspose.com</a>.</p>
                    <p style="margin:15px 0px;"><?php echo __('Get the', 'aspose-doc-exporter'); ?> <a href="#" target="_blank"><?php echo __('Manual here', 'aspose-doc-exporter'); ?></a>.</p>

                </div>
            </div>

            <div id="AsposeDocExporterOptions" class="postbox">
                <div title="Click to toggle" class="handlediv"><br /></div>
                <h3 class="hndle"><?php echo __('Review', 'aspose-doc-exporter'); ?></h3>
                <div class="inside">
                    <p style="margin:15px 0px;">
                        <?php echo __('Please feel free to add your reviews on', 'aspose-doc-exporter'); ?> <a href="http://wordpress.org/support/view/plugin-reviews/aspose-doc-exporter" target="_blank"><?php echo __('Wordpress', 'aspose-doc-exporter');?></a>.</p>
                    </p>

                </div>
            </div>
        </div>
    </div>

    <div id="post-body">
        <div id="post-body-content">
            <div id="WtiLikePostOptions" class="postbox">
                <h3><?php echo __('Configuration / Settings', 'aspose-doc-exporter'); ?></h3>

                <div class="inside">
                    <form method="post" action="options.php">
                        <?php settings_fields('aspose_doc_exporter_options'); ?>
                        <table class="form-table">



                            <tr valign="top">
                                <td colspan="2">
                                    <p> If you don't have an account with Aspose Cloud, <a target="_blank" href="https://cloud.aspose.com/SignUp?src=total-api"> Click here </a> to Sign Up.</p>
                                </td>

                            </tr>

                            <tr valign="top">
                                <th scope="row"><label><?php _e('App SID', 'aspose-doc-exporter'); ?></label></th>
                                <td>
                                    <input type="text" size="40" name="aspose_doc_exporter_app_sid" id="aspose_doc_exporter_app_sid" value="<?php echo get_option('aspose_doc_exporter_app_sid'); ?>" />
                                    <span class="description"><?php _e('Aspose for Cloud App sID.', 'aspose-doc-exporter');?></span>
                                </td>
                            </tr>

                            <tr valign="top">
                                <th scope="row"><label><?php _e('App key', 'aspose-doc-exporter'); ?></label></th>
                                <td>
                                    <input type="text" size="40" name="aspose_doc_exporter_app_key" id="aspose_doc_exporter_app_key" value="<?php echo get_option('aspose_doc_exporter_app_key'); ?>" />
                                    <span class="description"><?php _e('Aspose for Cloud App Key.', 'aspose-doc-exporter');?></span>
                                </td>
                            </tr>


                            <tr valign="top">
                                <th scope="row"></th>
                                <td>
                                    <input class="button-primary" type="submit" name="Save" value="<?php _e('Save Options', 'aspose-doc-exporter'); ?>" />
                                    <input class="button-secondary" type="reset" name="Reset" value="<?php _e('Reset', 'aspose-doc-exporter'); ?>" />
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
<?php
}

add_action( 'admin_footer',  'aspose_doc_exporter_add_export_option' );
function aspose_doc_exporter_add_export_option() {
    ?>
    <script type="text/javascript">
        jQuery(document).ready(function($) {
            jQuery('<option>').val('aspose_export_doc').text('<?php echo('Aspose Export To DOC')?>').appendTo("select[name='action']");
            jQuery('<option>').val('aspose_export_doc').text('<?php echo('Aspose Export To DOC')?>').appendTo("select[name='action2']");
        });
    </script>

<?php
}

add_action('load-edit.php','aspose_doc_exporter_bulk_action');

function aspose_doc_exporter_bulk_action(){

    $upload_dir = wp_upload_dir();
    $upload_path = $upload_dir['path'] . '/';

    $html_file = $upload_path . 'outpuut.html';
    $doc_file = $upload_path . 'outpuut.doc';

    @unlink($html_file);
    @unlink($doc_file);

    global $typenow;
    $post_type = $typenow;

    // get the action
    $wp_list_table = _get_list_table('WP_Posts_List_Table');  // depending on your resource type this could be WP_Users_List_Table, WP_Comments_List_Table, etc
    $action = $wp_list_table->current_action();

    $allowed_actions = array("aspose_export_doc");

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
        case 'aspose_export_doc':

            $exported = count($post_ids);

            $post_contents = aspose_doc_exporter_array_builder($post_ids);

            if ( is_array($post_contents) && count($post_contents) > 0 ) {
                $file_name = aspose_doc_exporter_array_to_html($post_contents);
                include_once('asposeDocConverter.php');
            } else {
                wp_die( __('Error exporting post.') );
            }



            $sendback = add_query_arg( array('exported' => $exported, 'ids' => join(',', $post_ids) ), $sendback );

            break;

        default:
            return;
    }



    $sendback = remove_query_arg( array('action', 'action2', 'tags_input', 'post_author', 'comment_status', 'ping_status', '_status',  'post', 'bulk_edit', 'post_view'), $sendback );

    wp_redirect($sendback);
    exit();
}

add_action('admin_notices','aspose_doc_exporter_admin_notices');

function aspose_doc_exporter_admin_notices() {

    $upload_dir = wp_upload_dir();
    $upload_path = $upload_dir['path'] . '/';

    $file_name = $upload_path . 'output.doc';

    $download_path = plugin_dir_url(__FILE__) . 'aspose_doc_exporter_download.php';
    global $post_type, $pagenow;

    if($pagenow == 'edit.php' && isset($_REQUEST['exported']) && (int) $_REQUEST['exported']) {
        $message = sprintf( _n( 'Post exported.', '%s posts exported.', $_REQUEST['exported'] ), number_format_i18n( $_REQUEST['exported'] ) );
        $message .='<a href="'.$download_path.'?file='.$file_name.'"> Click Here </a> to download the doc file.';
        echo "<div class=\"updated\"><p>{$message}</p></div>";
    }
}

function aspose_doc_exporter_array_builder($post_ids) {

    foreach($post_ids as $post_id) {

        $post_data = get_post($post_id);

        $post_title = $post_data->post_title;
        $post_content = apply_filters('the_content',$post_data->post_content) ;

        $post_contents[$post_title] = $post_content;
    }


    if(is_array($post_contents)) {
        return $post_contents;
    } else {
        return false;
    }

}

function aspose_doc_exporter_array_to_html($post_contents){

    $upload_dir = wp_upload_dir();
    $upload_path = $upload_dir['path'] . '/';
    $filename = 'output.html';

    $output_string = <<<EOD
<!DOCTYPE html>
<html>
    <head>
        <title></title>
    </head>
    <body>
EOD;

    foreach($post_contents as $post_title=>$post_content) {
        $output_string .= <<<EOD
        <h1> {$post_title} </h1>
        <p> {$post_content} </p>
        <hr />
EOD;
    }

    $output_string .= <<<EOD
</body>
</html>
EOD;

    file_put_contents($upload_path . $filename,$output_string);

    return $upload_path . $filename;
}

