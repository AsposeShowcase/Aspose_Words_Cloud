<?php
/*
Plugin Name: Aspose Doc Exporter
Plugin URI:
Description: Aspose Doc Exporter is a plugin for exporting contents of posts into the doc / docx file.
Version: 1.0
Author: Fahad Adeel
Author URI: http://cloud.aspose.com/

*/

#### INSTALLATION PROCESS ####
/*
1. Download the plugin and extract it
2. Upload the directory '/Aspose-Doc-Exporter/' to the '/wp-content/plugins/' directory
3. Activate the plugin through the 'Plugins' menu in WordPress
4. Click on 'Aspose Doc Exporter' link under Settings menu to access the admin section
*/

add_filter('plugin_action_links', 'AsposeDocExporterPluginLinks', 10, 2);

/**
 * Create the settings link for this plugin
 * @param $links array
 * @param $file string
 * @return $links array
 */
function AsposeDocExporterPluginLinks($links, $file) {
     static $this_plugin;

     if (!$this_plugin) {
		$this_plugin = plugin_basename(__FILE__);
     }

     if ($file == $this_plugin) {
		$settings_link = '<a href="' . admin_url('options-general.php?page=AsposeDocExporterAdminMenu') . '">' . __('Settings', 'Aspose-Doc-Exporter') . '</a>';
		array_unshift($links, $settings_link);
     }

     return $links;
}


/**
 * For removing options
 * @param no-param
 * @return no-return
 */
function UnsetOptionsAsposeDocExporter() {
    // Deleting the added options on plugin uninstall
    delete_option('aspose_doc_exporter_app_sid');
    delete_option('aspose_doc_exporter_app_key');

}

register_uninstall_hook(__FILE__, 'UnsetOptionsAsposeDocExporter');

function AsposeDocExporterAdminRegisterSettings() {
     // Registering the settings

     register_setting('aspose_doc_exporter_options', 'aspose_doc_exporter_app_sid');
     register_setting('aspose_doc_exporter_options', 'aspose_doc_exporter_app_key');

}

add_action('admin_init', 'AsposeDocExporterAdminRegisterSettings');


if (is_admin()) {
	// Include the file for loading plugin settings
	require_once('aspose_doc_exporter_admin.php');
}

