<?php
defined( '_JEXEC' ) or die( 'Restricted access' );

$jAp = JFactory::getApplication();
$AsposePdfImporterInput = $jAp->input;

$base_url = JURI::base();

$module_path = dirname(__FILE__);

// Get current document object
$document = JFactory::getDocument();

// Add stylesheets and javascripts specific to module

$AsposeParams = "var insert_pdf_url = '".$base_url."index.php?option=com_ajax&module=aspose_pdf_importer&format=json';
var uploadpath = '';
";


$document->addScriptDeclaration($AsposeParams);
$document->addStyleSheet($base_url . 'modules/mod_aspose_pdf_importer/tmpl/css/jquery-ui.css');
$document->addScript($base_url . 'modules/mod_aspose_pdf_importer/tmpl/js/jquery.form.js');
$document->addScript($base_url . 'modules/mod_aspose_pdf_importer/tmpl/js/jquery-ui.js');
$document->addScript($base_url . 'modules/mod_aspose_pdf_importer/tmpl/js/pdf_importer.js');


require_once $module_path . '/helper.php';

$query_string_option = $AsposePdfImporterInput->get('option');
$query_string_view = $AsposePdfImporterInput->get('view');
$query_string_layout = $AsposePdfImporterInput->get('layout');

if ($query_string_option == 'com_content' && $query_string_view == 'article' && $query_string_layout == 'edit') {
    // Include the syndicate functions only once
    $pdf_files_uploaded = ModAsposePdfImporterHelper::getAsposeFiles();
    require JModuleHelper::getLayoutPath('mod_aspose_pdf_importer', $params->get('layout', 'default'));
}