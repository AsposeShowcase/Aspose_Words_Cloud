<?php

/*
 * Including the sdk of php
 */


use Aspose\Cloud\Common\AsposeApp;
use Aspose\Cloud\Common\Product;
use Aspose\Cloud\Words\Converter;
use Aspose\Cloud\Words\Document;
use Aspose\Cloud\Words\DocumentBuilder;
use Aspose\Cloud\Words\Extractor;
use Aspose\Cloud\Storage\Folder;


function aspose_posts_exporter_autoloader($class) {
    $allowed_namespace = array('AsposeApp','Product','Converter','Utils','Folder','Document','DocumentBuilder','Extractor');
    $arr = explode('\\', $class);
    if( in_array( $arr['3'] , $allowed_namespace)){
        include 'Aspose_Cloud_SDK_For_PHP-master/src/'. $arr[0] . '/' . $arr[1] . '/' .$arr[2] . '/' . $arr[3] . '.php';
    }

}

spl_autoload_register('aspose_posts_exporter_autoloader');

$upload_dir = wp_upload_dir();
$upload_path = $upload_dir['path'] . '/';

/*
 *  Assign appSID and appKey of your Aspose App
 */
AsposeApp::$appSID = get_option('aspose_posts_exporter_app_sid');
AsposeApp::$appKey = get_option('aspose_posts_exporter_app_key');
AsposeApp::$outPutLocation = $upload_path;


/*
 * Assign Base Product URL
 */

Product::$baseProductUri = 'http://api.aspose.com/v1.1';
ini_set('max_execution_time', '1000000000');
global $ape_html_filename;
//$file_name = 'G:\xampp\htdocs\wordpress/wp-content/uploads/2015/01/test1.htm';
//$ape_html_filename = 'fahad111.docx';
$folder = new Folder();
$result = $folder->uploadFile($file_name, '');

if($result['Status'] == 'OK') {

	$func = new Converter($ape_html_filename);
    $func->saveFormat = 'docx';
    $func->convert(); //die("i m here");
    $doc_file_name = $upload_path . str_replace(array('.html', '.htm'),array('.docx', '.docx'),$ape_html_filename);
    $file_info = pathinfo($doc_file_name);

    $folder = new Folder();
    $result = $folder->uploadFile($doc_file_name, '');

    $cloud_doc_file = $file_info['filename'].'.'.$file_info['extension'];

    $func = new Document($cloud_doc_file);
    if($post_params['author_name']!='')
        $func->setProperty('Author',$post_params['author_name']);

    if($post_params['ebook_title']!='')
        $func->setProperty('Title',$post_params['ebook_title']);

    $func->setProperty('Company','Aspose');


    $func = new Converter($cloud_doc_file);
	$func->saveFormat = 'epub';
    $func->convert();

    $upload_dir = wp_upload_dir();
    $upload_path = $upload_dir['path'] . '/';
    $epub_file_name = $upload_path . str_replace(array('.html', '.htm'),array('.epub', '.epub'),$ape_html_filename);

    if($post_params['ebook_name'] !='' && false) {

        $epub_file_details = pathinfo($epub_file_name);
        $epub_file = $epub_file_details['filename'];
        $replace_epub_file_name = $post_params['ebook_name'];
        $epub_file_name = str_replace($epub_file,$replace_epub_file_name,$epub_file_name);
        $epub_file_name = str_replace(' ','_',$epub_file_name);

    }


    $download_path = plugin_dir_url(__FILE__) . 'aspose_posts_exporter_download.php';

    $message = '<a href="'.$download_path.'?file='.$epub_file_name.'"> Click Here </a> to download the doc file.';
    echo "<div class=\"updated\"><p>{$message}</p></div>";
}
