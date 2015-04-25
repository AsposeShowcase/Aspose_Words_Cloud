<?php
/*
Plugin Name: Aspose Doc Importer
Plugin URI:
Description: Aspose Doc Importer is a plugin for reading content from the Doc file and then inserting it in the editor.
Version: 2.0
Author: Fahad Adeel
Author URI: http://cloud.aspose.com/

*/

#### INSTALLATION PROCESS ####
/*
1. Download the plugin and extract it
2. Upload the directory '/Aspose-Doc-Importer/' to the '/wp-content/plugins/' directory
3. Activate the plugin through the 'Plugins' menu in WordPress
4. Click on 'Aspose Doc Importer' link under Settings menu to access the admin section
*/

add_filter('plugin_action_links', 'AsposeDocImporterPluginLinks', 10, 2);

/**
 * Create the settings link for this plugin
 * @param $links array
 * @param $file string
 * @return $links array
 */
function AsposeDocImporterPluginLinks($links, $file) {
     static $this_plugin;

     if (!$this_plugin) {
		$this_plugin = plugin_basename(__FILE__);
     }

     if ($file == $this_plugin) {
		$settings_link = '<a href="' . admin_url('options-general.php?page=AsposeDocImporterAdminMenu') . '">' . __('Settings', 'Aspose-Doc-Importer') . '</a>';
		array_unshift($links, $settings_link);
     }

     return $links;
}

register_activation_hook(__FILE__, 'SetOptionsAsposeDocImporter');

/**
 * Basic options function for the plugin settings
 * @param no-param
 * @return void
 */
function SetOptionsAsposeDocImporter() {

     // Adding options for the like post plugin
//     add_option('wti_like_post_drop_settings_table', '0', '', 'yes');

}

/**
 * For dropping the table and removing options
 * @param no-param
 * @return no-return
 */
function UnsetOptionsAsposeDocImporter() {
    // Deleting the added options on plugin uninstall
    delete_option('wti_like_post_drop_settings_table');
}

register_uninstall_hook(__FILE__, 'UnsetOptionsAsposeDocImporter');

function AsposeDocImporterAdminRegisterSettings() {
     // Registering the settings

     register_setting('aspose_doc_importer_options', 'aspose_doc_importer_app_sid');
     register_setting('aspose_doc_importer_options', 'aspose_doc_importer_app_key');

}

add_action('admin_init', 'AsposeDocImporterAdminRegisterSettings');


if (is_admin()) {
	// Include the file for loading plugin settings
	require_once('aspose_doc_importer_admin.php');
}

