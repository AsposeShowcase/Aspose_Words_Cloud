<?php
/**
 * Converts pages or document into different formats.
 */
namespace Aspose\Cloud\Slides;

use Aspose\Cloud\Common\AsposeApp;
use Aspose\Cloud\Common\Product;
use Aspose\Cloud\Common\Utils;
use Aspose\Cloud\Exception\AsposeCloudException as Exception;

class Converter
{

    public $fileName = '';
    public $saveFormat = '';

    public function __construct($fileName, $saveFormat = 'PPT')
    {
        //set default values
        $this->fileName = $fileName;

        $this->saveFormat = $saveFormat;
    }

    /**
     * Saves a particular slide into various formats with specified width and height.
     *
     * @param integer $slideNumber The number of slide.
     * @param string $imageFormat The image format.
     *
     * @return string Returns the file path.
     * @throws Exception
     */
    public function convertToImage($slideNumber, $imageFormat)
    {


        $strURI = Product::$baseProductUri . '/slides/' . $this->getFileName() . '/slides/' . $slideNumber . '?format=' . $imageFormat;

        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'GET', '', '');
        $v_output = Utils::validateOutput($responseStream);
        if ($v_output == '') {
            $outputPath = AsposeApp::$outPutLocation . Utils::getFileName($this->getFileName()) . '.' . $imageFormat;
            Utils::saveFile($responseStream, $outputPath);
            return $outputPath;
        } else {
            return $v_output;
        }
    }

    /**
     * Convert a particular slide into various formats with specified width and height.
     *
     * @param integer $slideNumber The slide number.
     * @param string $imageFormat The image format.
     * @param integer $width The width of image.
     * @param integer $height The height of image.
     *
     * @return string Returns the file path.
     * @throws Exception
     */
    public function convertToImagebySize($slideNumber, $imageFormat, $width, $height)
    {


        $strURI = Product::$baseProductUri . '/slides/' . $this->getFileName() . '/slides/' . $slideNumber . '?format=' . $imageFormat . '&width=' . $width . '&height=' . $height;

        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'GET', '', '');
        $v_output = Utils::validateOutput($responseStream);
        if ($v_output == '') {
            $outputPath = AsposeApp::$outPutLocation . 'output.' . $imageFormat;
            Utils::saveFile($responseStream, $outputPath);
            return $outputPath;
        } else {
            return $v_output;
        }
    }

    /**
     * Convert a document to the specified format.
     *
     * @return string Returns the file path.
     * @throws Exception
     */
    public function convert()
    {


        $strURI = Product::$baseProductUri . '/slides/' . $this->getFileName() . '?format=' . $this->saveFormat;

        $signedURI = Utils::sign($strURI);
        $responseStream = Utils::processCommand($signedURI, 'GET', '', '');

        $v_output = Utils::validateOutput($responseStream);

        if ($v_output === '') {
            return $responseStream;
        } else {
            return $v_output;
        }
    }
    
    /**
     * Convert a document to SaveFormat without using Aspose storage.
     * 
     * @param string $inputPath The path of source file.
     * @param string $outputFile Path where you want to file after conversion.
     * @param string $saveFormat New file format.
     * 
     * @return string|boolean Return the file path.
     * @throws Exception
     */
    public function convertLocalFile($inputPath, $outputFile, $saveFormat) {
        if ($inputPath == '')
            throw new Exception('Please specify input file');
            
        if ($outputFile == '') 
            throw new Exception('Please specify output file');
            
        if ($saveFormat == '') 
            throw new Exception('Please specify save format');
        
        $strURI = Product::$baseProductUri . '/slides/convert?format=' . $saveFormat;

        $signedURI = Utils::sign($strURI);
        
        $responseStream = Utils::uploadFileBinary($signedURI, $inputPath, 'xml');        

        $v_output = Utils::validateOutput($responseStream);
        
        if ($v_output === '') {
            if ($saveFormat == 'html') {
                $outputFormat = 'zip';
            } else {
                $outputFormat = $saveFormat;
            }
            
            $outputFileName = Utils::getFileName($outputFile) . '.' . $outputFormat;
            Utils::saveFile($responseStream, AsposeApp::$outPutLocation . $outputFileName);
            return $outputFileName;
        } else {
            return $v_output;
        }
    }

    /**
     * @return string
     */
    public function getFileName()
    {
        if ($this->fileName == '') {
            throw new Exception('No File Name Specified');
        }
        return $this->fileName;
    }

    /**
     * @param string $fileName
     */
    public function setFileName($fileName)
    {
        $this->fileName = $fileName;
        return $this;
    }

    /**
     * @return string
     */
    public function getSaveFormat()
    {
        return $this->saveFormat;
    }

    /**
     * @param string $saveFormat
     */
    public function setSaveFormat($saveFormat)
    {
        $this->saveFormat = $saveFormat;
        return $this;
    }

}