<?php
/*
Plugin Name: Aspose Cloud Presentation Importer
Plugin URI:
Description: Aspose Cloud Presentation Importer is a plugin for reading content from the Ppt file and then inserting it in the editor.
Version: 1.0
Author: Fahad Adeel
Author URI: http://cloud.aspose.com/

*/

#### INSTALLATION PROCESS ####
/*
1. Download the plugin and extract it
2. Upload the directory '/Aspose-Cloud-Presentation-Importer/' to the '/wp-content/plugins/' directory
3. Activate the plugin through the 'Plugins' menu in WordPress
4. Click on 'Aspose Cloud Presentation Importer' link under Settings menu to access the admin section
*/

add_filter('plugin_action_links', 'AsposePptImporterPluginLinks', 10, 2);

/**
 * Create the settings link for this plugin
 * @param $links array
 * @param $file string
 * @return $links array
 */
function AsposePptImporterPluginLinks($links, $file) {
     static $this_plugin;

     if (!$this_plugin) {
		$this_plugin = plugin_basename(__FILE__);
     }

     if ($file == $this_plugin) {
		$settings_link = '<a href="' . admin_url('options-general.php?page=AsposePptImporterAdminMenu') . '">' . __('Settings', 'Aspose-Ppt-Importer') . '</a>';
		array_unshift($links, $settings_link);
     }

     return $links;
}

register_activation_hook(__FILE__, 'SetOptionsAsposePptImporter');

/**
 * Basic options function for the plugin settings
 * @param no-param
 * @return void
 */
function SetOptionsAsposePptImporter() {

     // Adding options for the like post plugin
//     add_option('wti_like_post_drop_settings_table', '0', '', 'yes');

}

/**
 * For dropping the table and removing options
 * @param no-param
 * @return no-return
 */
function UnsetOptionsAsposePptImporter() {
    // Deleting the added options on plugin uninstall
    delete_option('wti_like_post_drop_settings_table');
}

register_uninstall_hook(__FILE__, 'UnsetOptionsAsposePptImporter');

function AsposePptImporterAdminRegisterSettings() {
     // Registering the settings

     register_setting('aspose_cloud_presentation_importer_options', 'aspose_cloud_presentation_importer_app_sid');
     register_setting('aspose_cloud_presentation_importer_options', 'aspose_cloud_presentation_importer_app_key');

}

add_action('admin_init', 'AsposePptImporterAdminRegisterSettings');


if (is_admin()) {
	// Include the file for loading plugin settings
	require_once('aspose_cloud_presentation_importer_admin.php');
}

