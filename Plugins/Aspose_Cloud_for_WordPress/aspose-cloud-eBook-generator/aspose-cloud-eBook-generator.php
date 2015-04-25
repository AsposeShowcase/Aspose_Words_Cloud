<?php
/*
Plugin Name: Aspose Cloud eBook Generator
Plugin URI:
Description: Aspose Cloud eBook Generator is a plugin for exporting content from Posts/Pages and then downloading it in desired format.
Version: 1.0
Author: Fahad Adeel
Author URI: http://cloud.aspose.com/

*/

function register_aspose_posts_exporter_menu_page() {
    add_menu_page( 'Aspose Cloud eBook Generator', 'Aspose Cloud eBook Generator', 'edit_published_posts', 'aspose-cloud-ebook-generator/ape-admin.php', 'display_aspose_posts_exporter_admin_page', 'dashicons-admin-page', 30 );
    add_options_page('Aspose Cloud eBook Generator', __('Aspose Cloud eBook Generator', 'aspose-cloud-ebook-generator'), 'activate_plugins', 'AsposePostsExporterAdminMenu', 'AsposePostsExporterAdminContent');
}

function display_aspose_posts_exporter_admin_page() {

    $ape_sid = get_option('aspose_posts_exporter_app_sid');
    $ape_key = get_option('aspose_posts_exporter_app_key');

    if(empty($ape_sid) || empty($ape_key)) {
        echo '<div><h2 style="color: red">Please enter Aspose SID and Key on plugin settings page.</h2></div>';
        return;
    }

    if(isset($_POST['ape-export_submit_button'])) {

        $post_params = $_POST;

        $query_args = array();

        $query_args['posts_per_page'] = -1;
        $query_args['date_query'] = array();

        // Query on Post types
        if(isset($post_params['ape-allposts-export_post_types']) && !empty($post_params['ape-allposts-export_post_types'])) {
            $query_args['post_type'] = $post_params['ape-allposts-export_post_types'];
        }

        // Query on Author
        if(isset($post_params['ape-allposts-export_authors']) && !empty($post_params['ape-allposts-export_authors'])) {
            $query_args['author__in'] = $post_params['ape-allposts-export_authors'];
        }

        // Query on Category
        if(isset($post_params['ape-allposts-export_categories']) && !empty($post_params['ape-allposts-export_categories'])) {
            $query_args['category__in'] = $post_params['ape-allposts-export_categories'];
        }

        // Query on Date Range
        if(isset($post_params['ape-allposts-export_from']) && !empty($post_params['ape-allposts-export_from'])) {
            $query_args['date_query'][] = array(
                    'column' => 'post_date_gmt',
                    'after'  => strtotime($post_params['ape-allposts-export_from']),
                );
        }

        // Query on Date Range
        if(isset($post_params['ape-allposts-export_to']) && !empty($post_params['ape-allposts-export_to'])) {
            $query_args['date_query'][] = array(
                    'column' => 'post_date_gmt',
                    'before' => strtotime($post_params['ape-allposts-export_to']),
                );
        }


        $the_query = new WP_Query( $query_args );

        $filtered_post_ids = array();

        if ( $the_query->have_posts() ) {
            while ( $the_query->have_posts() ) : $the_query->the_post();
                $filtered_post_ids[] = get_the_ID();
            endwhile;
            wp_reset_query();


            if(($key = array_search($post_params['intro_page_id'], $filtered_post_ids)) !== false) {
                unset($filtered_post_ids[$key]);
            }

            array_unshift($filtered_post_ids,$post_params['intro_page_id']);

            $posts_contents = aspose_posts_exporter_array_builder($filtered_post_ids,$post_params);

            if ( is_array($posts_contents) && count($posts_contents) > 0 ) {
                $file_name = aspose_posts_exporter_array_to_html($posts_contents);

                $message1 = count($posts_contents) . ' found, matching your criteria.';
                echo "<div class=\"updated\"><p>{$message1}</p></div>";

                include_once('aspose_posts_exporter_doc_converter.php');
            } else {
                wp_die( __('Error exporting post.') );
            }
        } else {
            $message2 = 'Sorry, no posts matched your criteria.';
            echo "<div class=\"error\"><p>{$message2}</p></div>";
        }

        //die('done');
    }

    require_once('ape-admin-main.php');
}

add_action( 'admin_menu', 'register_aspose_posts_exporter_menu_page' );

function aspose_posts_exporter_enqueue_scripts() {
    wp_register_script('APEScript', plugins_url('js/script.js', __FILE__ ), array('jquery'));
    wp_register_style('APEStyle', plugins_url('css/style.css', __FILE__), array(), '');

    $js_params = array();
    wp_localize_script('APEScript', 'APE_Params', $js_params);

    wp_enqueue_script('APEScript');
    wp_enqueue_style('APEStyle');

    // jQuery UI Scripts / Styles
    wp_enqueue_script('jquery-ui-datepicker');
    wp_enqueue_script('jquery-ui-tabs');

    wp_enqueue_style( 'jquery-ui-tabs');
    wp_enqueue_style('APE-jquery-ui-style', 'http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.2/themes/smoothness/jquery-ui.css');

    define('APE_PLUGIN_BASE_PATH', plugin_dir_path(__FILE__));
}

add_action('admin_init', 'aspose_posts_exporter_enqueue_scripts');

function includeSourceLinktoPost($content) {
    return $content . "<p>" . "<a href=\"".get_the_permalink(get_the_ID())."\"> Source </a> </p> ";
}

function includeDatetoPostTitle($title){

    return $title . '<p><small><em>' . get_the_time('F j, Y',get_the_ID()) .'</em></small></p>';
}

function aspose_posts_exporter_array_builder($post_ids,$post_params) {

    if($post_params['show_source_link'] == 'yes'){
        add_filter('the_content',includeSourceLinktoPost);
    }

    if($post_params['show_post_date'] == 'yes'){
        add_filter('the_title',includeDatetoPostTitle);
    }

    $count = 0;
    foreach($post_ids as $post_id) {
        if($count == 1){
            $post_contents['Copyright'] = $post_params['copy_right_details'];
        }
        $post_data = get_post($post_id);

        $post_title = apply_filters('the_title',$post_data->post_title);
        $post_content = apply_filters('the_content',$post_data->post_content) ;
       // $post_content = $post_data->post_content;

        $post_contents[$post_title] = $post_content;
        $count ++;
    }

    $post_contents['Colophon'] = $post_params['colophon_details'];


    if(is_array($post_contents)) {
        return $post_contents;
    } else {
        return false;
    }

}

function aspose_posts_exporter_array_to_html($post_contents){
    global $ape_html_filename;
    $upload_dir = wp_upload_dir();
    $upload_path = $upload_dir['path'] . '/';
    $ape_html_filename = 'output_'.time().'.htm';

    $output_string = <<<EOD
<!DOCTYPE html>
<html>
<body>
EOD;

    foreach($post_contents as $key => $value) {
        $output_string .= <<<EOD
        <h1 id="{$key}"> {$key} </h1>
        <p> {$value} </p>
        <hr />
EOD;
    }

    $output_string .= <<<EOD
</body>
</html>
EOD;

    @unlink($upload_path . $ape_html_filename);
    file_put_contents($upload_path . $ape_html_filename, $output_string);

    return $upload_path . $ape_html_filename;
}



/**
 * Pluing settings page
 * @param no-param
 * @return no-return
 */
function AsposePostsExporterAdminContent() {

    // Creating the admin configuration interface
    ?>
    <div class="wrap">
    <h2><?php echo __('Aspose Cloud eBook Generator Options', 'aspose-cloud-ebook-generator');?></h2>
    <br class="clear" />

    <div class="metabox-holder has-right-sidebar" id="poststuff">
    <div class="inner-sidebar" id="side-info-column">
        <div class="meta-box-sortables ui-sortable" id="side-sortables">
            <div id="AsposePostsExporterOptions" class="postbox">
                <div title="Click to toggle" class="handlediv"><br /></div>
                <h3 class="hndle"><?php echo __('Support / Manual', 'aspose-cloud-ebook-generator'); ?></h3>
                <div class="inside">
                    <p style="margin:15px 0px;"><?php echo __('For any suggestion / query / issue / requirement, please feel free to drop an email to', 'aspose-cloud-ebook-generator'); ?> <a href="/cdn-cgi/l/email-protection#87eae6f5ece2f3f7ebe6e4e2c7e6f4f7e8f4e2a9e4e8eab8f4f2e5ede2e4f3bac6f4f7e8f4e2a7c3e8e4a7c2fff7e8f5f3e2f5">marketplace@aspose.com</a>.</p>
                    <p style="margin:15px 0px;"><?php echo __('Get the', 'aspose-cloud-ebook-generator'); ?> <a href="#" target="_blank"><?php echo __('Manual here', 'aspose-cloud-ebook-generator'); ?></a>.</p>

                </div>
            </div>

            <div id="AsposePostsExporterOptions" class="postbox">
                <div title="Click to toggle" class="handlediv"><br /></div>
                <h3 class="hndle"><?php echo __('Review', 'aspose-cloud-ebook-generator'); ?></h3>
                <div class="inside">
                    <p style="margin:15px 0px;">
                        <?php echo __('Please feel free to add your reviews on', 'aspose-cloud-ebook-generator'); ?> <a href="http://wordpress.org/support/view/plugin-reviews/aspose-cloud-ebook-generator" target="_blank"><?php echo __('Wordpress', 'aspose-cloud-ebook-generator');?></a>.</p>
                    </p>

                </div>
            </div>
        </div>
    </div>

    <div id="post-body">
        <div id="post-body-content">
            <div id="WtiLikePostOptions" class="postbox">
                <h3><?php echo __('Configuration / Settings', 'aspose-cloud-ebook-generator'); ?></h3>

                <div class="inside">
                    <form method="post" action="options.php">
                        <?php settings_fields('aspose_posts_exporter_options'); ?>
                        <table class="form-table">



                            <tr valign="top">
                                <td colspan="2">
                                    <p> If you don't have an account with Aspose Cloud, <a target="_blank" href="https://cloud.aspose.com/SignUp?src=total-api"> Click here </a> to Sign Up.</p>
                                </td>

                            </tr>

                            <tr valign="top">
                                <th scope="row"><label><?php _e('App SID', 'aspose-cloud-ebook-generator'); ?></label></th>
                                <td>
                                    <input type="text" size="40" name="aspose_posts_exporter_app_sid" id="aspose_posts_exporter_app_sid" value="<?php echo get_option('aspose_posts_exporter_app_sid'); ?>" />
                                    <span class="description"><?php _e('Aspose for Cloud App sID.', 'aspose-cloud-ebook-generator');?></span>
                                </td>
                            </tr>

                            <tr valign="top">
                                <th scope="row"><label><?php _e('App key', 'aspose-cloud-ebook-generator'); ?></label></th>
                                <td>
                                    <input type="text" size="40" name="aspose_posts_exporter_app_key" id="aspose_posts_exporter_app_key" value="<?php echo get_option('aspose_posts_exporter_app_key'); ?>" />
                                    <span class="description"><?php _e('Aspose for Cloud App Key.', 'aspose-cloud-ebook-generator');?></span>
                                </td>
                            </tr>


                            <tr valign="top">
                                <th scope="row"></th>
                                <td>
                                    <input class="button-primary" type="submit" name="Save" value="<?php _e('Save Options', 'aspose-cloud-ebook-generator'); ?>" />
                                    <input class="button-secondary" type="reset" name="Reset" value="<?php _e('Reset', 'aspose-cloud-ebook-generator'); ?>" />
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

add_filter('plugin_action_links', 'AsposePostsExporterPluginLinks', 10, 2);

/**
 * Create the settings link for this plugin
 * @param $links array
 * @param $file string
 * @return $links array
 */
function AsposePostsExporterPluginLinks($links, $file) {
    static $this_plugin;

    if (!$this_plugin) {
        $this_plugin = plugin_basename(__FILE__);
    }

    if ($file == $this_plugin) {
        $settings_link = '<a href="' . admin_url('options-general.php?page=AsposePostsExporterAdminMenu') . '">' . __('Settings', 'aspose-cloud-ebook-generator') . '</a>';
        array_unshift($links, $settings_link);
    }

    return $links;
}


/**
 * For removing options
 * @param no-param
 * @return no-return
 */
function UnsetOptionsAsposePostsExporter() {
    // Deleting the added options on plugin uninstall
    delete_option('aspose_posts_exporter_app_sid');
    delete_option('aspose_posts_exporter_app_key');

}

register_uninstall_hook(__FILE__, 'UnsetOptionsAsposePostsExporter');

function AsposePostsExporterAdminRegisterSettings() {
    // Registering the settings

    register_setting('aspose_posts_exporter_options', 'aspose_posts_exporter_app_sid');
    register_setting('aspose_posts_exporter_options', 'aspose_posts_exporter_app_key');

}

add_action('admin_init', 'AsposePostsExporterAdminRegisterSettings');