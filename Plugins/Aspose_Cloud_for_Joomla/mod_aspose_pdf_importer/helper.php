<?php
defined( '_JEXEC' ) or die( 'Restricted access' );


class ModAsposePdfImporterHelper
{
    public $app_sid;
    public $app_key;

    public static function getAjax()
    {
       
        $module_path = dirname(__FILE__);

        JLoader::registerNamespace('Aspose', $module_path . '/Aspose_Cloud_SDK_For_PHP-master/src');

        $jAp = JFactory::getApplication();
        $myJoomlaInput = $jAp->input;

        // Get Module Configurations
        $module_params = static::getParams();

        // Set Application ID and Application Key for Aspose SDK
        $app_id = $module_params->get('pdf_importer_app_sid', false);
        $app_key = $module_params->get('pdf_importer_app_key', false);
        $aspose_pdf_importer_option = $module_params->get('aspose_pdf_importer_option', false);

        if(!$app_id || !$app_key || empty($app_id) || empty($app_key)) {
            die("APP Id/ APP Key for Aspose SDK not found");
        }


        \Aspose\Cloud\Common\AsposeApp::$appSID = $app_id;
        \Aspose\Cloud\Common\AsposeApp::$appKey = $app_key;

        /*
         * Assign Base Product URL
         */
        \Aspose\Cloud\Common\Product::$baseProductUri = 'http://api.aspose.com/v1.1';

        $uploadpath = JPATH_ADMINISTRATOR . "/" . "modules" . "/" . "mod_aspose_pdf_importer" . "/" . "uploads/";

        \Aspose\Cloud\Common\AsposeApp::$outPutLocation = $uploadpath;

        $file_received = $myJoomlaInput->files->get('filePath');

        if(is_array($file_received) && count($file_received) > 0 ) {

            jimport('joomla.filesystem.file');

            //Clean up filename to get rid of strange characters like spaces etc
            $uploadfilename = time() . '_' . JFile::makeSafe($file_received['name']);

            //Set up the source and destination of the file
            $src = $file_received['tmp_name'];
            $dest = JPATH_ADMINISTRATOR . "/" . "modules" . "/" . "mod_aspose_pdf_importer" . "/" . "uploads" . "/" . $uploadfilename;

            //First check if the file has the right extension, we need pdf only
            if (strtolower(JFile::getExt($uploadfilename) ) == 'pdf') {

                if (JFile::upload($src, $dest)) {
                    $folder = new \Aspose\Cloud\Storage\Folder();
                    $uploadpath = str_replace("\\","/",$dest);

                    $uploadFile = $dest;
                    $folder->uploadFile($uploadFile, '');


                } else {
                    die("There is some problem in uploading File.");
                }


            } else {
                die("Wrong File was selected!");
            }
        }



        if(isset($_REQUEST['filename']) && !empty($_REQUEST['filename'])){
            $filename = $_REQUEST['filename'];
        } else {
            $filename = $uploadfilename;
        }


        $ext = pathinfo($filename, PATHINFO_EXTENSION);

        $ext = strtolower($ext);

        if($ext == 'pdf') {

            \Aspose\Cloud\Common\AsposeApp::$outPutLocation = $uploadpath; // 'F:\\xampp\htdocs\\wordpress\\uploads\\';


            $filename = trim($filename);

            if($aspose_pdf_importer_option == 'html') {

                $converter = new \Aspose\Cloud\Pdf\Converter($filename);
                $converter->saveFormat = 'html';
                $converter->fileName = $filename;
                $saved_file = $converter->convert();
                $targetdir_arr = pathinfo($saved_file);

                ModAsposePdfImporterHelper::unzip($targetdir_arr['dirname'] .'/' . str_replace('.zip','',$targetdir_arr['basename']),$targetdir_arr['dirname'] . '/' . $targetdir_arr['basename']);
                $file_name = $targetdir_arr['dirname'] . '/' . str_replace('.zip','',$targetdir_arr['basename']) . '/' . \Aspose\Cloud\Common\Utils::getFileName($filename) . '.html';
                $file_name = str_replace("\\","/",$file_name);
                $file_html = file_get_contents($file_name);
                $file_html_folder = \Aspose\Cloud\Common\Utils::getFileName($filename) . '_files';

                $destination = JURI::base(). "modules" . "/" . "mod_aspose_pdf_importer" . "/" . "uploads/";

                $file_html = str_replace($file_html_folder,$destination.'/'.\Aspose\Cloud\Common\Utils::getFileName($filename).'/'.$file_html_folder,$file_html);

                $css_file = @file_get_contents($uploadpath.'/'.\Aspose\Cloud\Common\Utils::getFileName($filename).'/'.$file_html_folder.'/style.css');

                $css_array = ModAsposePdfImporterHelper::parse($css_file);

                preg_match_all('/class="([^"]+)"/i',$file_html,$matches);


                foreach($matches[1] as $key=>$class_name){

                    $classes_arr = explode(' ',$class_name);
                    $style_text = '';
                    if(is_array($classes_arr) && count($classes_arr) > 0){

                        foreach($classes_arr as $c){
                            $key_index = @$css_array['.'.$c];

                            if(@is_array($css_array['.'.$c])){
                                foreach($css_array['.'.$c] as $style_key=>$style_value) {

                                    if($style_key!='' && $style_value !='' && $style_key!='font-family'){
                                        $style_text .= $style_key .':' . $style_value . ';';
                                    }
                                }
                            }



                        }
                    }
                    if($style_text != ''){
                        $replace_string = 'style="'.$style_text.'"';
                        $c_n = 'class="'.$class_name.'"';
                        $file_html = str_replace($c_n,$replace_string,$file_html);
                    }
                }
                preg_match_all('/style="([^"]+)" style="([^"]+)"/',$file_html,$match_arr);

                foreach($match_arr['0'] as $key=>$m){
                    $file_html = str_replace($m,'style="'.$match_arr['1'][$key] . $match_arr['2'][$key] .'"',$file_html);
                }

                $content = $file_html;

            } else {
                $func = new \Aspose\Cloud\Pdf\TextEditor($filename);

                $output = $func->getText();

                $output_arr = explode('.',$output);
                $content = '';
                foreach($output_arr as $output){
                    $content .= '<p>' . $output . '</p>';
                }
            }


            die($content);
        } else {
            die("Wrong File was selected!");
        }

    }

    public static function getAsposeFiles()
    {
        $module_path = dirname(__FILE__);

        JLoader::registerNamespace('Aspose', $module_path . '/Aspose_Cloud_SDK_For_PHP-master/src');

        $jAp = JFactory::getApplication();
        $myJoomlaInput = $jAp->input;

        // Get Module Configurations
        $module_params = static::getParams();

        // Set Application ID and Application Key for Aspose SDK
        $app_id = $module_params->get('pdf_importer_app_sid', false);
        $app_key = $module_params->get('pdf_importer_app_key', false);

        \Aspose\Cloud\Common\AsposeApp::$appSID = $app_id;
        \Aspose\Cloud\Common\AsposeApp::$appKey = $app_key;

        /*
         * Assign Base Product URL
         */
        \Aspose\Cloud\Common\Product::$baseProductUri = 'http://api.aspose.com/v1.1';

        if(isset($_REQUEST['aspose_folder']) && !empty($_REQUEST['aspose_folder'])) {
            $aspose_folder = $_REQUEST['aspose_folder'];
        } else {
            $aspose_folder = '';
        }

        $folder = new \Aspose\Cloud\Storage\Folder();

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


        return $result;


    }

    public static function getParams($instance = 'mod_aspose_pdf_importer')
    { // replace mod_your_module
        jimport('joomla.application.module.helper');
        $module = JModuleHelper::getModule($instance);
        $moduleParams = new JRegistry;
        $moduleParams->loadString($module->params);

        return $moduleParams;
    }

    public static function unzip($dir, $file, $destiny="")
    {
        //echo $dir . "<HR>". $file; exit;
        $dir .= DIRECTORY_SEPARATOR;
        $path_file = $file;
        $zip = zip_open($path_file);
        $_tmp = array();
        $count=0;
        if ($zip)
        {
            while ($zip_entry = zip_read($zip))
            {
                $_tmp[$count]["filename"] = zip_entry_name($zip_entry);
                $_tmp[$count]["stored_filename"] = zip_entry_name($zip_entry);
                $_tmp[$count]["size"] = zip_entry_filesize($zip_entry);
                $_tmp[$count]["compressed_size"] = zip_entry_compressedsize($zip_entry);
                $_tmp[$count]["mtime"] = "";
                $_tmp[$count]["comment"] = "";
                $_tmp[$count]["folder"] = dirname(zip_entry_name($zip_entry));
                $_tmp[$count]["index"] = $count;
                $_tmp[$count]["status"] = "ok";
                $_tmp[$count]["method"] = zip_entry_compressionmethod($zip_entry);

                if (zip_entry_open($zip, $zip_entry, "r"))
                {
                    $buf = zip_entry_read($zip_entry, zip_entry_filesize($zip_entry));
                    if($destiny)
                    {
                        $path_file = str_replace("/",DIRECTORY_SEPARATOR, $destiny . zip_entry_name($zip_entry));
                    }
                    else
                    {
                        $path_file = str_replace("/",DIRECTORY_SEPARATOR, $dir . zip_entry_name($zip_entry));
                    }
                    $new_dir = dirname($path_file);

                    // Create Recursive Directory

                    @mkdir($new_dir);


                    $fp = @fopen($dir . zip_entry_name($zip_entry), "w");
                    @fwrite($fp, $buf);
                    @fclose($fp);

                    @zip_entry_close($zip_entry);
                }

                $count++;
            }

            zip_close($zip);
        }
    }

    public static function parse($css){
        //$css = file_get_contents($file);
        preg_match_all( '/(?ims)([a-z0-9\s\.\:#_\-@,]+)\{([^\}]*)\}/', $css, $arr);
        $result = array();
        foreach ($arr[0] as $i => $x){
            $selector = trim($arr[1][$i]);
            $rules = explode(';', trim($arr[2][$i]));
            $rules_arr = array();
            foreach ($rules as $strRule){
                if (!empty($strRule)){
                    $rule = explode(":", $strRule);
                    $rules_arr[trim($rule[0])] = trim($rule[1]);
                }
            }

            $selectors = explode(',', trim($selector));
            foreach ($selectors as $strSel){
                $result[$strSel] = $rules_arr;
            }
        }
        return $result;
    }
}
