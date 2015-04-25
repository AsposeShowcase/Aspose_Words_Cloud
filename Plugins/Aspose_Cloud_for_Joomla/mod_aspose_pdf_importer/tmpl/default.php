<?php
defined( '_JEXEC' ) or die( 'Restricted access' );
?>

<div class="btn-toolbar">
    <button class="modal btn" onclick="load_uploader();">Aspose PDF Importer</button>

    <div id="aspose_pdf_popup_container" style="display: none;">
        <ul>
            <li><a href="#upload-new">Select File From Local</a></li>
            <li><a href="#uploaded-files">Select File From Aspose Cloud</a></li>
        </ul>
        <div id="upload-new">
            <fieldset id="pdf-reader-form" class="pdf-reader-form">

                <form class="my-form" id="my_file_form" action="index.php?option=com_ajax&module=aspose_pdf_importer&format=json" enctype="multipart/form-data" method="post">
                    <input type="file" id="pdf_file_selected" name="filePath" />
                    <input type="hidden" id="old_pdf_file_selected" name="filename" />
                    <input class="btn btn-warning" type="button" id="submit_btn" value="Extract PDF Contents" />
                </form>
            </fieldset>
        </div>
        <div id="uploaded-files">
            <div style="float: right;">
                <input type="button" class="btn" id="insert_aspose_pdf_content" value="Select File"/>
            </div>
            <div style="width: 100%;float: left;">
                <?php
                    echo $pdf_files_uploaded;// End Foreach
                ?>
            </div>
        </div>
    </div>
</div>
<div class="modal_loading"><!-- Place at bottom of page --></div>