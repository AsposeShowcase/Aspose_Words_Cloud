<?php
/**
 * Deals with Word document builder aspects.
 */
namespace Aspose\Cloud\Words;

use Aspose\Cloud\Common\AsposeApp;
use Aspose\Cloud\Common\Product;
use Aspose\Cloud\Common\Utils;
use Aspose\Cloud\Exception\AsposeCloudException as Exception;
use Aspose\Cloud\Storage\Folder;

class MailMerge
{

    /*
     * Executes mail merge without regions.
     * 
     * @param string $fileName The source file name.
     * @param string $strXML Data in xml format.
     * 
     * @return string Returns the file path.
     * @throws Exception
     */
    public function executeMailMerge($fileName, $strXML)
    {
        //check whether files are set or not
        if ($fileName == '')
            throw new Exception('File not specified');

        //build URI to execute mail merge without regions
        $strURI = Product::$baseProductUri . '/words/' . $fileName . '/executeMailMerge';

        //sign URI
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'POST', '', $strXML);
        $json = json_decode($responseStream);

        $v_output = Utils::validateOutput($responseStream);

        if ($v_output === '') {
            //Save docs on server
            $folder = new Folder();
            $outputStream = $folder->GetFile($json->Document->FileName);
            $outputPath = AsposeApp::$outPutLocation . $fileName;
            Utils::saveFile($outputStream, $outputPath);
            return $outputPath;
        } else
            return $v_output;
    }

    /**
     * Executes mail merge with regions.
     *
     * @param string $fileName The name of source file.
     * @param string $strXML Data in xml format.
     *
     * @return string Returns the file path.
     * @throws Exception
     */
    public function executeMailMergewithRegions($fileName, $strXML)
    {
        //check whether files are set or not
        if ($fileName == '')
            throw new Exception('File not specified');

        //build URI to execute mail merge with regions
        $strURI = Product::$baseProductUri . '/words/' . $fileName . '/executeMailMerge?withRegions=true';

        //sign URI
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'POST', '', $strXML);
        $json = json_decode($responseStream);

        $v_output = Utils::validateOutput($responseStream);

        if ($v_output === '') {
            //Save docs on server
            $folder = new Folder();
            $outputStream = $folder->GetFile($json->Document->FileName);
            $outputPath = AsposeApp::$outPutLocation . $fileName;
            Utils::saveFile($outputStream, $outputPath);
            return $outputPath;
        } else
            return $v_output;
    }

    /**
     * Executes mail merge template.
     *
     * @param string $fileName The name of source file.
     * @param string $strXML Data in xml format.
     * @param string $documentFolder The document folder.
     *
     * @return string Returns the file path.
     * @throws Exception
     */
    public function executeTemplate($fileName, $strXML, $documentFolder = '')
    {
        //check whether files are set or not
        if ($fileName == '')
            throw new Exception('File not specified');

        //build URI to execute mail merge template
        $strURI = Product::$baseProductUri . '/words/' . $fileName . '/executeTemplate' . ($documentFolder == '' ? '' : '?folder=' . $documentFolder);

        //sign URI
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'POST', '', $strXML);

        $v_output = Utils::validateOutput($responseStream);

        if ($v_output === '') {
            $json = json_decode($responseStream);
            //Save docs on server
            $folder = new Folder();
            $outputStream = $folder->GetFile(($documentFolder == '' ? $json->Document->FileName : $documentFolder . '/' . $json->Document->FileName));
            $outputPath = AsposeApp::$outPutLocation . $fileName;
            Utils::saveFile($outputStream, $outputPath);
            return $outputPath;
        } else
            return $v_output;
    }

}