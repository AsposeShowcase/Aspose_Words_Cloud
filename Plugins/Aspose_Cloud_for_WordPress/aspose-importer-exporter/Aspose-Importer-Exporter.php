<?php
/*
Plugin Name: Aspose Importer & Exporter
Plugin URI:
Description: Aspose Importer Exporter is a plugin for reading content from different file formats and inserting them into the editor. Also It exports psots / page contents to different formats. 
version: 1.0
Author: Fahad Adeel
Author URI: http://cloud.aspose.com/

*/

#### INSTALLATION PROCESS ####
/*
1. Download the plugin and extract it
2. Upload the directory '/Aspose-Importer-Exporter/' to the '/wp-content/plugins/' directory
3. Activate the plugin through the 'Plugins' menu in WordPress
4. Click on 'Aspose Importer Exporter' link under Settings menu to access the admin section
*/

if(get_option('display_aspose_drop_down_on_front') == '1') {
    add_filter( 'the_content', 'aspose_export_feature_post');
}

function aspose_export_feature_post($content) {

    global $post;

    $post_id = $post->ID;

    $base_name = plugin_basename(__FILE__);
    $base_name = explode('/',$base_name);
    $base_name = $base_name['0'];

    $download_path = site_url() . '/index.php?' . $base_name . '=aspose_export_posts';
    //http://localhost/wordpress3.9/index.php?aspose-importer-exporter=aspose_import_export_download
    $post_ids = array($_REQUEST['post_id']);

    //require_once('aspose_import_export_download.php');

	$export_content = '<p>';
	$export_content .= '<a target = "_new" href = "'.$download_path.'&action=export&format=pdf&post_id='.$post_id.'">Export to PDF</a>';
	$export_content .= ' | ';
	$export_content .= '<a target = "_new" href = "'.$download_path.'&action=export&format=doc&post_id='.$post_id.'">Export to DOC</a>';
	$export_content .= '</p>';

	return $export_content . $content;
}

add_filter('plugin_action_links', 'AsposeImportExportPluginLinks', 10, 2);

/**
 * Create the settings link for this plugin
 * @param $links array
 * @param $file string
 * @return $links array
 */
function AsposeImportExportPluginLinks($links, $file) {
     static $this_plugin;

     if (!$this_plugin) {
		$this_plugin = plugin_basename(__FILE__);
     }

     if ($file == $this_plugin) {
		$settings_link = '<a href="' . admin_url('options-general.php?page=AsposeImportExportAdminMenu') . '">' . __('Settings', 'Aspose-Importer-Exporter') . '</a>';
		array_unshift($links, $settings_link);
     }

     return $links;
}

register_activation_hook(__FILE__, 'SetOptionsAsposeImportExport');

/**
 * Basic options function for the plugin settings
 * @param no-param
 * @return void
 */
function SetOptionsAsposeImportExport() {


}

/**
 * For dropping the table and removing options
 * @param no-param
 * @return no-return
 */
function UnsetOptionsAsposeImportExport() {
    // Deleting the added options on plugin uninstall
    
}

register_uninstall_hook(__FILE__, 'UnsetOptionsAsposeImportExport');

function AsposeImportExportAdminRegisterSettings() {
     // Registering the settings

     register_setting('aspose_import_export_options', 'aspose_import_export_app_sid');
     register_setting('aspose_import_export_options', 'aspose_import_export_app_key');
     register_setting('aspose_import_export_options', 'display_aspose_drop_down_on_front');

}

add_action('admin_init', 'AsposeImportExportAdminRegisterSettings');

require_once('aspose-common-functions.php');
require_once('aspose-import-export-admin.php');
require_once('aspose-files-import.php');
require_once('aspose-files-export.php');
require_once('aspose_export_posts.php');


