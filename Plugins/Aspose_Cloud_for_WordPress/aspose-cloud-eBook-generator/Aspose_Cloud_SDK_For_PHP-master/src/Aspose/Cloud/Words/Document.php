<?php
/**
 * Deals with Word document level aspects.
 */
namespace Aspose\Cloud\Words;

use Aspose\Cloud\Common\AsposeApp;
use Aspose\Cloud\Common\Utils;
use Aspose\Cloud\Common\Product;
use Aspose\Cloud\Storage\Folder;
use Aspose\Cloud\Exception\AsposeCloudException as Exception;

class Document {

    public $fileName = '';

    public function __construct($fileName)
    {
        $this->fileName = $fileName;
    }

    /**
     * Update all document fields.
     * 
     * @return boolean
     * @throws Exception
     */
    public function updateFields()
    {
        //build URI to merge Docs
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/updateFields';

        //sign URI
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'POST', '', '');

        $json = json_decode($responseStream);

        if ($json->Code == 200)
            return true;
        else
            return false;
    }

    /**
     * Reject all tracking changes.
     * 
     * @return boolean
     * @throws Exception
     */
    public function rejectTrackingChanges()
    {
        //build URI to merge Docs
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/revisions/rejectAll';

        //sign URI
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'POST', '', '');

        $json = json_decode($responseStream);


        if ($json->Code == 200)
            return true;
        else
            return false;
    }

    /**
     * Accept all tracking changes.
     * 
     * @return boolean
     * @throws Exception
     */
    public function acceptTrackingChanges()
    {
        //build URI to merge Docs
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/revisions/acceptAll';

        //sign URI
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'POST', '', '');

        $json = json_decode($responseStream);


        if ($json->Code == 200)
            return true;
        else
            return false;
    }

    /**
     * Get Document's stats.
     * 
     * @return object|boolean
     * @throws Exception
     */
    public function getStats()
    {
        //build URI to merge Docs
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/statistics';

        //sign URI
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'GET', '', '');

        $json = json_decode($responseStream);


        if ($json->Code == 200)
            return $json->StatData;
        else
            return false;
    }

    /**
     * @param string $from From page number.
     * @param string $to To page number.
     * @param string $format Returns document in the specified format.
     * @param string $storageName Name of the storage.
     * @param string $folder Name of the folder.
     * 
     * @return string|boolean
     * @throws Exception
     */

    public function splitDocument($from='' ,$to='', $format='pdf', $storageName = '', $folder = '')
    {
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/split?';

        if ($folder != '') {
            $strURI .= '&folder=' . $folder;
        }

        if ($storageName != '') {
            $strURI .= '&storage=' . $storageName;
        }

        if ($from != '') {
            $strURI .= '&from=' . $from;
        }

        if ($to != '') {
            $strURI .= '&to=' . $to;
        }

        if ($format != '') {
            $strURI .= '&format=' . $format;
        }

        $strURI = rtrim($strURI,'?');
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'POST', '', '');

        $json = json_decode($responseStream);

        if ($json->Code == 200) {
            foreach ($json->SplitResult->Pages as $splitPage) {
                $splitFileName = basename($splitPage->Href);

                //build URI to download split slides
                $strURI = Product::$baseProductUri . '/storage/file/' . $splitFileName;
                //sign URI
                $signedURI = Utils::Sign($strURI);
                $responseStream = Utils::processCommand($signedURI, "GET", "", "");
                //save split slides
                $outputFile = AsposeApp::$outPutLocation . $splitFileName;
                Utils::saveFile($responseStream, $outputFile);
            }
            return $json->SplitResult->Pages;
        }
        else
            return false;

    }

    /**
     * Appends a list of documents to this one.
     * 
     * @param string $appendDocs List of documents to append.
     * @param string $importFormatModes Documents import format modes.
     * @param string $sourceFolder Name of the folder where documents are present.
     * 
     * @return string Returns the file path.
     * @throws Exception
     */
    public function appendDocument($appendDocs, $importFormatModes, $sourceFolder)
    {
        //check whether required information is complete
        if (count($appendDocs) != count($importFormatModes))
            throw new Exception('Please specify complete documents and import format modes');

        $post_array = array();
        $i = 0;
        foreach ($appendDocs as $doc) {
            $post_array[] = array("Href" => (($sourceFolder != "" ) ? $sourceFolder . "\\" . $doc : $doc), "ImportFormatMode" => $importFormatModes[$i]);
            $i++;
        }
        $data = array("DocumentEntries" => $post_array);
        $json = json_encode($data);

        //build URI to merge Docs
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/appendDocument';

        //sign URI
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'POST', 'json', $json);

        $v_output = Utils::validateOutput($responseStream);

        if ($v_output === '') {
            //Save merged docs on server
            $folder = new Folder();
            $outputStream = $folder->GetFile($sourceFolder . (($sourceFolder == '') ? '' : '/') . $this->getFileName());
            $outputPath = AsposeApp::$outPutLocation . $this->getFileName();
            Utils::saveFile($outputStream, $outputPath);
            return $outputPath;
        }
        else
            return $v_output;
    }

    /**
     * Get Resource Properties information like document source format, 
     * IsEncrypted, IsSigned and document properties
     * 
     * @return object|boolean
     * @throws Exception
     */
    public function getDocumentInfo()
    {
        //build URI to merge Docs
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName();

        //sign URI
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'GET', '', '');

        $json = json_decode($responseStream);

        if ($json->Code == 200)
            return $json->Document;
        else
            return false;
    }

    /**
     * Get Resource Properties information like document source format, 
     * IsEncrypted, IsSigned and document properties
     * 
     * @param string $propertyName The name of property.
     * 
     * @return object|boolean
     * @throws Exception
     */
    public function getProperty($propertyName)
    {
        if ($propertyName == '')
            throw new Exception('Property Name not specified');

        //build URI to merge Docs
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/documentProperties/' . $propertyName;

        //sign URI
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'GET', '', '');

        $json = json_decode($responseStream);


        if ($json->Code == 200)
            return $json->DocumentProperty;
        else
            return false;
    }

    /**
     * Set document property.
     * 
     * @param string $propertyName The name of property.
     * @param string $propertyValue The value of property.
     * 
     * @return object|boolean
     * @throws Exception
     */
    public function setProperty($propertyName, $propertyValue)
    {
        if ($propertyName == '')
            throw new Exception('Property Name not specified');

        if ($propertyValue == '')
            throw new Exception('Property Value not specified');

        //build URI to merge Docs
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/documentProperties/' . $propertyName;

        $put_data_arr['Value'] = $propertyValue;

        $put_data = json_encode($put_data_arr);

        //sign URI
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'PUT', 'json', $put_data);

        $json = json_decode($responseStream);

        if ($json->Code == 200)
            return $json->DocumentProperty;
        else
            return false;
    }
    
    /**
     * Protect a document on the Aspose cloud storage.
     * 
     * @param type $password Document protection password. 
     * @param type $protectionType Document protection type, one from: AllowOnlyComments, AllowOnlyFormFields, AllowOnlyRevisions, ReadOnly, NoProtection. 
     * 
     * @return string Returns the file path.
     * @throws Exception
     */
    public function protectDocument($password, $protectionType = 'AllowOnlyComments')
    {
        if ($password == '') {
            throw new Exception('Please Specify A Password');
        }
        $fieldsArray = array('Password' => $password, 'ProtectionType' => $protectionType);
        $json = json_encode($fieldsArray);
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/protection';
        $signedURI = Utils::sign($strURI);
        $responseStream = Utils::processCommand($signedURI, 'PUT', 'json', $json);
        $v_output = Utils::validateOutput($responseStream);
        if ($v_output === '') {
            $strURI = Product::$baseProductUri . '/storage/file/' . $this->getFileName();
            $signedURI = Utils::sign($strURI);
            $responseStream = Utils::processCommand($signedURI, 'GET', '', '');
            $outputFile = AsposeApp::$outPutLocation . $this->getFileName();
            Utils::saveFile($responseStream, $outputFile);
            return $outputFile;
        }
        else
            return $v_output;
    }

    /**
     * Unprotect a document on the Aspose cloud storage.
     * 
     * @param type $password Current document protection password.
     * @param type $protectionType Document protection type, one from: AllowOnlyComments, AllowOnlyFormFields, AllowOnlyRevisions, ReadOnly, NoProtection. 
     * 
     * @return string Returns the file path.
     * @throws Exception
     */
    public function unprotectDocument($password, $protectionType = 'AllowOnlyComments')
    {
        if ($password == '') {
            throw new Exception('Please Specify A Password');
        }
        $fieldsArray = array('Password' => $password, 'ProtectionType' => $protectionType);
        $json = json_encode($fieldsArray);
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/protection';
        $signedURI = Utils::sign($strURI);
        $responseStream = Utils::processCommand($signedURI, 'DELETE', 'json', $json);
        $v_output = Utils::validateOutput($responseStream);
        if ($v_output === '') {
            $strURI = Product::$baseProductUri . '/storage/file/' . $this->getFileName();
            $signedURI = Utils::sign($strURI);
            $responseStream = Utils::processCommand($signedURI, 'GET', '', '');
            $outputFile = AsposeApp::$outPutLocation . $this->getFileName();
            Utils::saveFile($responseStream, $outputFile);
            return $outputFile;
        }
        else
            return $v_output;
    }
    
    /**
     * Update document protection.
     * 
     * @param string $oldPassword Current document protection password.
     * @param string $newPassword New document protection password. 
     * @param string $protectionType Document protection type.
     * 
     * @return string Returns the file path.
     * @throws Exception
     */
    public function updateProtection($oldPassword, $newPassword, $protectionType = 'AllowOnlyComments')
    {
        if ($oldPassword == '') {
            throw new Exception('Please Specify Old Password');
        }
        if ($newPassword == '') {
            throw new Exception('Please Specify New Password');
        }
        $fieldsArray = array('Password' => $oldPassword, 'NewPassword' => $newPassword, 'ProtectionType' => $protectionType);
        $json = json_encode($fieldsArray);
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/protection';
        $signedURI = Utils::sign($strURI);
        $responseStream = Utils::processCommand($signedURI, 'POST', 'json', $json);
        $v_output = Utils::validateOutput($responseStream);
        if ($v_output === '') {
            $strURI = Product::$baseProductUri . '/storage/file/' . $this->getFileName();
            $signedURI = Utils::sign($strURI);
            $responseStream = Utils::processCommand($signedURI, 'GET', '', '');
            $outputFile = AsposeApp::$outPutLocation . $this->getFileName();
            Utils::saveFile($responseStream, $outputFile);
            return $outputFile;
        }
        else
            return $v_output;
    }

    /**
     * Delete a document property.
     * 
     * @param string $propertyName The name of property.
     * 
     * @return boolean
     * @throws Exception
     */
    public function deleteProperty($propertyName)
    {
        if ($propertyName == '')
            throw new Exception('Property Name not specified');

        //build URI to merge Docs
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/documentProperties/' . $propertyName;

        //sign URI
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'DELETE', '', '');

        $json = json_decode($responseStream);

        if ($json->Code == 200)
            return true;
        else
            return false;
    }

    /**
     * Get Document's properties.
     * 
     * @return array
     * @throws Exception
     */
    public function getProperties()
    {
        //build URI to merge Docs
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/documentProperties';

        //sign URI
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'GET', '', '');

        $json = json_decode($responseStream);


        if ($json->Code == 200)
            return $json->DocumentProperties->List;
        else
            return false;
    }

    /*
     * Convert Document to different file format without using storage.
     * 
     * $param string $inputPath The source file path.
     * @param string $outputPath Output directory path.
     * @param string $outputFormat Newly converted file format.
     * 
     * @return string Returns the file path.
     * @throws Exception
     */
    public function convertLocalFile($inputPath = '', $outputPath = '', $outputFormat = '')
    {
        //check whether file is set or not
        if ($inputPath == '')
            throw new Exception('No file name specified');

        if ($outputFormat == '')
            throw new Exception('output format not specified');


        $strURI = Product::$baseProductUri . '/words/convert?format=' . $outputFormat;

        if (!file_exists($inputPath)) {
            throw new Exception('input file doesnt exist.');
        }


        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::uploadFileBinary($signedURI, $inputPath, 'xml');

        $v_output = Utils::validateOutput($responseStream);

        if ($v_output === '') {

            $save_format = $outputFormat;

            if ($outputPath == '') {
                $outputPath = Utils::getFileName($inputPath) . '.' . $save_format;
            }
            $output =  AsposeApp::$outPutLocation . $outputPath;
            Utils::saveFile($responseStream,$output);
            return true;
        }
        else
            return $v_output;
    }

    /*
     * Save Document to different file formats.
     *
     * $param string $options_xml.

     * @return string Returns the file path.
     * @throws Exception
     */

    public function saveAs($options_xml = '')
    {
        if ($options_xml == '')
            throw new Exception('Options not specified.');

        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/saveAs';
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'POST', 'XML', $options_xml,'json');

        $json = json_decode($responseStream);

        if ($json->Code == 200){
            $outputFile = $json->SaveResult->DestDocument->Href;
            $strURI = Product::$baseProductUri . '/storage/file/'.$outputFile.'';
            $signedURI = Utils::sign($strURI);
            $responseStream = Utils::processCommand($signedURI, 'GET');

            $v_output = Utils::validateOutput($responseStream);

            if ($v_output === '') {

                $output =  AsposeApp::$outPutLocation . $outputFile;
                Utils::saveFile($responseStream,$output);
                return $output;
            }
            else
                return $v_output;

        }
        else {
            return false;
        }

    }

    /*
     * get a list of all sections present in a Word document.
     *

     * @return Object of all sections.
     * @throws Exception
     */

    public function getAllSections()
    {
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/sections';
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'GET','');

        $json = json_decode($responseStream);
        if ($json->Code == 200)
            return $json->Sections->SectionLinkList;
        else
            return false;

    }

    /*
     * get specefic section present in a Word document.
     *
     * $param string $filename.
     * $param string $sectionid.

     * @return Object of specefic section.
     * @throws Exception
     */

    public function getSection($sectionid = '')
    {
        if ($sectionid == '')
            throw new Exception('No Section Id specified');

        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/sections/'.$sectionid.'';
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'GET','');

        $json = json_decode($responseStream);

        if ($json->Code == 200)
            return $json->Section;
        else
            return false;

    }
    
    /*
     * Remove all Headers and Footers
     *
     * @return Boolean
     * @throws Exception
     */
    public function removeAllHeadersFooters()
    {
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/headersFooters';
        
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'DELETE', '', '');

        $json = json_decode($responseStream);

        if ($json->Code == 200)
            return true;
        else
            return false;

    }

    /**
     * get page setup information from any section of a Word document.
     *
     * $param string $filename.
     * $param string $sectionid.

     * @return Object of page setup information.
     * @throws Exception
     */
    public function getPageSetup($sectionid = '')
    {
        if ($sectionid == '')
            throw new Exception('No Section Id specified');

        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/sections/'.$sectionid.'/pageSetup';
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'GET','');

        $json = json_decode($responseStream);

        if ($json->Code == 200)
            return $json->PageSetup;
        else
            return false;

    }

    /**
     * update page setup information from any section of a Word document.
     *
     * $param string $filename.
     * $param string $sectionid.

     * @return Object of page setup information.
     * @throws Exception
     */
    public function updatePageSetup($options_xml = '',$sectionid = '')
    {
        if ($options_xml == '')
            throw new Exception('No Options specified');

        if ($sectionid == '')
            throw new Exception('No Section Id specified');

        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/sections/'.$sectionid.'/pageSetup';
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'POST', 'XML', $options_xml,'json');

        $json = json_decode($responseStream);

        if ($json->Code == 200)
            return $json->PageSetup;
        else
            return false;

    }

    /**
     * get mail merge and mustache field names.
     *
     * $param string $filename.

     * @return Object of Field Names.
     * @throws Exception
     */
    public function getMailMergeFieldNames()
    {
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/mailMergeFieldNames';
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'GET','');

        $json = json_decode($responseStream);

        if ($json->Code == 200)
            return $json->FieldNames;
        else
            return false;

    }

    /**
     * get a list of all paragraphs present in a Word document.
     *
     * $param string $filename.

     * @return Object of All Paragraphs.
     * @throws Exception
     */
    public function getAllParagraphs()
    {
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/paragraphs';
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'GET','');

        $json = json_decode($responseStream);

        if ($json->Code == 200)
            return $json->Paragraphs->ParagraphLinkList;
        else
            return false;

    }

    /**
     * get specefic paragraphs present in a Word document.
     *
     * $param string $filename.
     * $param string $paragraphid.

     * @return Object of Specefic Paragraphs.
     * @throws Exception
     */
    public function getParagraph($paragraphid = '')
    {
        if ($paragraphid == '')
            throw new Exception('No Paragraph Id specified');

        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/paragraphs/'.$paragraphid.'';
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'GET','');

        $json = json_decode($responseStream);

        if ($json->Code == 200)
            return $json->Paragraph;
        else
            return false;

    }

    /**
     * get any run of any paragraph from a Word document.
     *
     * $param string $filename.
     * $param string $paragraphid.
     * $param string $runid.

     * @return Object of Specefic Run.
     * @throws Exception
     */
    public function getParagraphRun($paragraphid = '',$runid = '')
    {
        if ($paragraphid == '')
            throw new Exception('No Paragraph Id specified');

        if ($runid == '')
            throw new Exception('No Run Id specified');

        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/paragraphs/'.$paragraphid.'/runs/'.$runid.'';
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'GET','');

        $json = json_decode($responseStream);

        if ($json->Code == 200)
            return $json->Run;
        else
            return false;

    }

    /**
     * get font information from any run of a paragraph.
     *
     * $param string $paragraphid.
     * $param string $runid.

     * @return Object of Font.
     * @throws Exception
     */
    public function getParagraphRunFont($paragraphid = '', $runid = '')
    {
        if ($paragraphid == '')
            throw new Exception('No Paragraph Id specified');

        if ($runid == '')
            throw new Exception('No Run Id specified');

        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/paragraphs/'.$paragraphid.'/runs/'.$runid.'/font';
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'GET','');

        $json = json_decode($responseStream);

        if ($json->Code == 200)
            return $json->Font;
        else
            return false;
    }

    /**
     * update font information from any run of a paragraph.
     *
     * $param string $options_xml.
     * $param string $paragraphid.
     * $param string $runid.

     * @return Object of Font.
     * @throws Exception
     */
    public function updateParagraphRunFont($options_xml = '', $paragraphid = '', $runid = '')
    {
        if ($options_xml == '')
            throw new Exception('Options not specified.');

        if ($paragraphid == '')
            throw new Exception('No Paragraph Id specified');

        if ($runid == '')
            throw new Exception('No Run Id specified');

        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/paragraphs/'.$paragraphid.'/runs/'.$runid.'/font';
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'POST', 'XML', $options_xml,'json');

        $json = json_decode($responseStream);

        if ($json->Code == 200)
            return $json->Font;
        else
            return false;

    }
    
    /**
     * Get all Hyperlinks from a Word
     * 
     * @return array|boolean
     * @throws Exception
     */
    public function getHyperlinks()
    {
        //build URI
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/hyperlinks';

        //sign URI
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'GET', '', '');

        $json = json_decode($responseStream);        

        if ($json->Code == 200)
            return $json->Hyperlinks->HyperlinkList;
        else
            return false;
    }
    
    /**
     * Get a Particular Hyperlink from a Word
     * 
     * @param int $hyperlinkIndex The index of hyperlink.
     * 
     * @return object|boolean
     * @throws Exception
     */
    public function getHyperlink($hyperlinkIndex)
    {
        if ($hyperlinkIndex == '')
            throw new Exception('Hyperlink index not specified');
        
        //build URI
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/hyperlinks/' . $hyperlinkIndex;

        //sign URI
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'GET', '', '');

        $json = json_decode($responseStream);        

        if ($json->Code == 200)
            return $json->Hyperlink;
        else
            return false;
    }
    
    /**
     * Get Hyperlinks Count from a Word
     * 
     * @return int|boolean
     * @throws Exception
     */
    public function getHyperlinksCount()
    {
        //build URI
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/hyperlinks';

        //sign URI
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'GET', '', '');

        $json = json_decode($responseStream);        

        if ($json->Code == 200)
            return count($json->Hyperlinks->HyperlinkList);
        else
            return false;
    }
    
    /**
     * Get all Bookmarks from a Word
     * 
     * @return array|boolean
     * @throws Exception
     */
    public function getBookmarks()
    {
        //build URI
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/bookmarks';

        //sign URI
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'GET', '', '');

        $json = json_decode($responseStream);        
        
        if ($json->Code == 200)
            return $json->Bookmarks->BookmarkList;
        else
            return false;
    }
    
    /**
     * Get a Specific Bookmark from a Word
     * 
     * @param string $bookmarkName Name of the Bookmark.
     * 
     * @return object|boolean
     * @throws Exception
     */
    public function getBookmark($bookmarkName)
    {
        if ($bookmarkName == '')
            throw new Exception('Bookmark name not specified');
        
        //build URI
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/bookmarks/' . $bookmarkName;

        //sign URI
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'GET', '', '');

        $json = json_decode($responseStream);        
        
        if ($json->Code == 200)
            return $json->Bookmark;
        else
            return false;
    }
    
    /**
     * Get Bookmarks count from a Word
     * 
     * @return int|boolean
     * @throws Exception
     */
    public function getBookmarksCount()
    {
        //build URI
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/bookmarks';

        //sign URI
        $signedURI = Utils::sign($strURI);

        $responseStream = Utils::processCommand($signedURI, 'GET', '', '');

        $json = json_decode($responseStream);        
        
        if ($json->Code == 200)
            return count($json->Bookmarks->BookmarkList);
        else
            return false;
    }
    
    /**
     * Update Bookmark Text of a Word
     * 
     * @return boolean
     * @throws Exception
     */
    public function updateBookmark($bookmarkName, $bookmarkText)
    {
        if ($bookmarkName == '')
            throw new Exception('Bookmark name not specified');
        
        if ($bookmarkText == '')
            throw new Exception('Bookmark text not specified');
        
        //build URI
        $strURI = Product::$baseProductUri . '/words/' . $this->getFileName() . '/bookmarks/' . $bookmarkName;

        //sign URI
        $signedURI = Utils::sign($strURI);
        
        $post_data_arr['Text'] = $bookmarkText;
        $postData = json_encode($post_data_arr);
        $responseStream = Utils::processCommand($signedURI, 'POST', 'JSON', $postData);

        $json = json_decode($responseStream);        
        
        if ($json->Code == 200)
            return true;
        else
            return false;
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

}