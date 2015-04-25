<?php
/**
 * Template file containing HTML Markup to render module UI.
 */
?>
<!-- Button to show on each edit/add content form. -->
<input type="button" value="Aspose PDF Importer" onclick="load_uploader();" id="edit-btn-select-pdf"
       class="form-submit"/>

<!-- Dialog box content is loaded from the below. -->
<div id="aspose_pdf_popup_container">
  <!-- Dialog tabs. -->
  <ul>
    <li><a href="#upload-new">Select File From Local</a></li>
    <li><a href="#uploaded-files">Select File From Aspose Cloud</a></li>
  </ul>
  <!-- Dialog tabs end here. -->

  <!-- Upload new file tab. -->
  <div id="upload-new">
    <fieldset id="pdf-reader-form" class="pdf-reader-form">
      <input type="file" id="pdf_file_selected" name="filePath"/>
      <input type="hidden" id="old_pdf_file_selected" name="filename"/>
      <input class="form-submit" type="button" data-submit-to="<?php echo $base_url . '/ajax/UploadPdfToCloud'; ?>"
             id="submit_btn" value="Extract PDF Contents"/>
    </fieldset>
  </div>
  <!-- Upload new file tab end here. -->
  <!-- Select existing file tab. -->
  <div id="uploaded-files">
    <div style="float: right;">
      <input type="button" class="form-submit" data-submit-to="<?php echo $base_url . '/ajax/UploadPdfToCloud'; ?>"
             id="insert_aspose_pdf_content" value="Select File"/>
    </div>
    <div id="cloud_files" style="width: 100%;float: left;">
      <?php
      // Get files list from cloud.
      $result = aspose_pdf_importer_cloud_files();
      echo $result;
      ?>
    </div>
  </div>
  <!-- Select existing file tab end here. -->
</div>

<!-- Div that shows loader. -->
<div class="modal"></div>
