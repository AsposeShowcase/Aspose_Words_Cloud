<?php

/*
 * Including the sdk of php
 */


use Aspose\Cloud\Common\AsposeApp;
use Aspose\Cloud\Common\Product;
use Aspose\Cloud\Storage\Folder;
use Aspose\Cloud\Pdf\TextEditor;


function my_autoloader($class) {
    $allowed_namespace = array('AsposeApp','Product','Folder','Converter','Utils','TextEditor');
    $arr = explode('\\', $class);
    if( in_array( $arr['3'] , $allowed_namespace)){
        include 'Aspose_Cloud_SDK_For_PHP-master/src/'. $arr[0] . '/' . $arr[1] . '/' .$arr[2] . '/' . $arr[3] . '.php';
    }

}

spl_autoload_register('my_autoloader');


/*
 *  Assign appSID and appKey of your Aspose App
 */
AsposeApp::$appSID = $_REQUEST['appSID']; // '8EB6E644-4A40-4B50-8012-135D1F8F7513';
AsposeApp::$appKey = $_REQUEST['appKey'];

/*
 * Assign Base Product URL
 */
Product::$baseProductUri = 'http://api.aspose.com/v1.1';

if(isset($_REQUEST['aspose_folder']) && !empty($_REQUEST['aspose_folder'])) {
    $aspose_folder = $_REQUEST['aspose_folder'];
} else {
    $aspose_folder = '';
}


$folder = new Folder();

$files = $folder->getFilesList($aspose_folder);

$asposeFolders = array();
$asposeFiles = array();
$options = '<option> --- Select Folder --- </option>';
$aspose_files_rows = "
    <tr>
                    <td width=\"5%\"> </td>
					<td width=\"95%\" ><strong> File Name </strong> </td>

                  </tr>
";
foreach($files as $file){
    if($file->IsFolder == '1'){
        array_push($asposeFolders,$file->Name);
        $options .= '<option value="'.$file->Name.'">'.$file->Name.'</option>';
    } else {
        array_push($asposeFiles,$file->Name);
        if($aspose_folder !=''){
            $aspose_folder = $aspose_folder . '/';
        }
        $aspose_files_rows .= '
            <tr>
                <td> <input type="radio" name="aspose_filename" value="'.$aspose_folder . $file->Name.'" /> </td>
                <td> '.$file->Name.' </td>
            </tr>
        ';
    }
}
if(is_array($asposeFolders) && count($asposeFolders) > 0) {
    $select_aspose_folder = '<select name="aspose_folder_name" id="aspose_folder_name">';
    $select_aspose_folder .= $options;
    $select_aspose_folder .='</select>';
} else {
    $select_aspose_folder = '';
}

if(count($asposeFiles) < 1){
    $aspose_files_rows = '
        <tr>
            <td colspan="2"> No Files Found. </td>
        </tr>
    ';
}


$result = '<tr>
		<td>'.$select_aspose_folder.'</td>
		</tr>
		<tr>
		<td>
		 <table id="bucket_files" cellspacing="8" width="100%">
		    '.$aspose_files_rows.'
		 </table>
		</td>
		</tr>
	';


echo $result;

