<?php

/**
 * Create the admin menu for this plugin
 * @param no-param
 * @return no-return
 */
function AsposeImportExportAdminMenu() {
     add_options_page('Aspose Importer & Exporter', __('Aspose Importer & Exporter', 'aspose-importer-exporter'), 'activate_plugins', 'AsposeImportExportAdminMenu', 'AsposeImportExportAdminContent');
}

add_action('admin_menu', 'AsposeImportExportAdminMenu');


/**
 * Pluing settings page
 * @param no-param
 * @return no-return
 */
function AsposeImportExportAdminContent() {

     // Creating the admin configuration interface
?>
<div class="wrap">
     <h2><?php echo __('Aspose Importer & Exporter Options', 'aspose-importer-exporter');?></h2>
     <br class="clear" />
	
	<div class="metabox-holder has-right-sidebar" id="poststuff">
		<div class="inner-sidebar" id="side-info-column">
			<div class="meta-box-sortables ui-sortable" id="side-sortables">
				<div id="AsposePdfImporterOptions" class="postbox">
					<div title="Click to toggle" class="handlediv"><br /></div>
					<h3 class="hndle"><?php echo __('Support / Manual', 'aspose-importer-exporter'); ?></h3>
					<div class="inside">
						<p style="margin:15px 0px;"><?php echo __('For any suggestion / query / issue / requirement, please feel free to drop an email to', 'aspose-importer-exporter'); ?> <a href="mailto:marketplace@aspose.com?subject=WordPress Aspose Importer & Exporter Plugin">marketplace@aspose.com</a>.</p>
						<p style="margin:15px 0px;"><?php echo __('Get the', 'aspose-importer-exporter'); ?> <a href="#" target="_blank"><?php echo __('Manual here', 'aspose-importer-exporter'); ?></a>.</p>

					</div>
				</div>

				<div id="AsposeImportExportOptions" class="postbox">
					<div title="Click to toggle" class="handlediv"><br /></div>
					<h3 class="hndle"><?php echo __('Review', 'aspose-importer-exporter'); ?></h3>
					<div class="inside">
						<p style="margin:15px 0px;">
							<?php echo __('Please feel free to add your reviews on', 'aspose-importer-exporter'); ?> <a href="http://wordpress.org/support/view/plugin-reviews/aspose-importer-exporter" target="_blank"><?php echo __('Wordpress', 'aspose-importer-exporter');?></a>.</p>
						</p>

					</div>
				</div>
			</div>
		</div>

		<div id="post-body">
			<div id="post-body-content">
				<div id="AsposeImportExportOptions" class="postbox">
					<h3><?php echo __('Configuration / Settings', 'aspose-importer-exporter'); ?></h3>

					<div class="inside">
						<form method="post" action="options.php">
							<?php settings_fields('aspose_import_export_options'); ?>
							<table class="form-table">



                                <tr valign="top">
                                    <td colspan="2">
                                        <p> If you don't have an account with Aspose Cloud, <a target="_blank" href="https://cloud.aspose.com/SignUp?src=total-api"> Click here </a> to Sign Up.</p>
                                    </td>

                                </tr>

                                <tr valign="top">
									<th scope="row"><label><?php _e('App SID', 'aspose-importer-exporter'); ?></label></th>
									<td>	
										<input type="text" size="40" name="aspose_import_export_app_sid" id="aspose_import_export_app_sid" value="<?php echo get_option('aspose_import_export_app_sid'); ?>" />
										<span class="description"><?php _e('Aspose for Cloud App sID.', 'aspose-importer-exporter');?></span>
									</td>
								</tr>

                                <tr valign="top">
                                    <th scope="row"><label><?php _e('App key', 'aspose-importer-exporter'); ?></label></th>
                                    <td>
                                        <input type="text" size="40" name="aspose_import_export_app_key" id="aspose_import_export_app_key" value="<?php echo get_option('aspose_import_export_app_key'); ?>" />
                                        <span class="description"><?php _e('Aspose for Cloud App Key.', 'aspose-importer-exporter');?></span>
                                    </td>
                                </tr>
								
								<tr valign="top">
                                    <th scope="row"><label><?php _e('Show Export Feature on Front', 'aspose-importer-exporter'); ?></label></th>
                                    <td>
                                        <input type="checkbox" name="display_aspose_drop_down_on_front" id="display_aspose_drop_down_on_front" value="1" <?php echo ( get_option('display_aspose_drop_down_on_front') == '1' ? 'checked="checked"' : '' ); ?> />
                                        
                                    </td>
                                </tr>


								<tr valign="top">
									<th scope="row"></th>
									<td>
										<input class="button-primary" type="submit" name="Save" value="<?php _e('Save Options', 'aspose-importer-exporter'); ?>" />
										<input class="button-secondary" type="reset" name="Reset" value="<?php _e('Reset', 'aspose-importer-exporter'); ?>" />
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
<?php
}