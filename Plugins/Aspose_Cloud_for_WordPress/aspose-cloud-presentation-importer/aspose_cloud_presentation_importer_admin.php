<?php

/**
 * Create the admin menu for this plugin
 * @param no-param
 * @return no-return
 */
function AsposePptImporterAdminMenu() {
     add_options_page('Aspose Cloud Presentation Importer', __('Aspose Cloud Presentation Importer', 'aspose-ppt-importer'), 'activate_plugins', 'AsposePptImporterAdminMenu', 'AsposePptImporterAdminContent');
}

add_action('admin_menu', 'AsposePptImporterAdminMenu');


/**
 * Add the javascript for the plugin
 * @param no-param
 * @return string
 */
function AsposePptImporterEnqueueScripts() {

    wp_register_script( 'aspose_cloud_presentation_importer_script', plugins_url( 'js/aspose_cloud_presentation_importer.js', __FILE__ ), array('jquery') );

    $upload_path = wp_upload_dir();
    $params = array(
        'appSID'            => get_option('aspose_cloud_presentation_importer_app_sid'),
        'appKey'            => get_option('aspose_cloud_presentation_importer_app_key'),
        'uploadpath'        => $upload_path['path'],
        'insert_ppt_url'    => plugins_url( 'getAsposePptContent.php', __FILE__ ),
        'aspose_files_url'    => plugins_url( 'getAsposeFiles.php', __FILE__ ),

    );

    wp_localize_script( 'aspose_cloud_presentation_importer_script', 'AsposeParams', $params );

    wp_enqueue_script( 'jquery-ui-dialog' );
    wp_enqueue_script( 'jquery-ui-tabs' );
    wp_enqueue_script( 'aspose_cloud_presentation_importer_script' );

    wp_register_style( 'AsposePptImporterStyle', plugins_url( 'css/style.css', __FILE__), array(), '' );

    wp_enqueue_style( 'AsposePptImporterStyle');
    wp_enqueue_style( 'jquery-ui-tabs');
    wp_enqueue_style( 'wp-jquery-ui-dialog');



}

add_action('init', 'AsposePptImporterEnqueueScripts');


/**
 * Pluing settings page
 * @param no-param
 * @return no-return
 */
function AsposePptImporterAdminContent() {

     // Creating the admin configuration interface
?>
<div class="wrap">
     <h2><?php echo __('Aspose Cloud Presentation Importer', 'aspose-ppt-importer');?></h2>
     <br class="clear" />
	
	<div class="metabox-holder has-right-sidebar" id="poststuff">
		<div class="inner-sidebar" id="side-info-column">
			<div class="meta-box-sortables ui-sortable" id="side-sortables">
				<div id="AsposePptImporterOptions" class="postbox">
					<div title="Click to toggle" class="handlediv"><br /></div>
					<h3 class="hndle"><?php echo __('Support / Manual', 'aspose-ppt-importer'); ?></h3>
					<div class="inside">
						<p style="margin:15px 0px;"><?php echo __('For any suggestion / query / issue / requirement, please feel free to drop an email to', 'aspose-ppt-importer'); ?> <a href="mailto:fahad.adeel@aspose.com?subject=Aspose Cloud Presentation Importer">fahad.adeel@aspose.com</a>.</p>
						<p style="margin:15px 0px;"><?php echo __('Get the', 'aspose-cloud presentation-importer'); ?> <a href="http://www.aspose.com/blogs/aspose-products/aspose-slides-product-family/archive/2015/01/05/wordpress-import-from-ppt-using-aspose.slides.html" target="_blank"><?php echo __('Manual here', 'aspose-cloud presentation-importer'); ?></a>.</p>

					</div>
				</div>

				<div id="AsposePptImporterOptions" class="postbox">
					<div title="Click to toggle" class="handlediv"><br /></div>
					<h3 class="hndle"><?php echo __('Review', 'aspose-cloud-presentation-importer'); ?></h3>
					<div class="inside">
						<p style="margin:15px 0px;">
							<?php echo __('Please feel free to add your reviews on', 'aspose-cloud-presentation-importer'); ?> <a href="http://wordpress.org/support/view/plugin-reviews/aspose-cloud-importer" target="_blank"><?php echo __('Wordpress', 'aspose-ppt-importer');?></a>.</p>
						</p>

					</div>
				</div>
			</div>
		</div>

		<div id="post-body">
			<div id="post-body-content">
				<div id="WtiLikePostOptions" class="postbox">
					<h3><?php echo __('Configuration / Settings', 'aspose-ppt-importer'); ?></h3>

					<div class="inside">
						<form method="post" action="options.php">
							<?php settings_fields('aspose_cloud_presentation_importer_options'); ?>
							<table class="form-table">



                                <tr valign="top">
                                    <td colspan="2">
                                        <p> If you don't have an account with Aspose Cloud, <a target="_blank" href="https://cloud.aspose.com/SignUp?src=total-api"> Click here </a> to Sign Up.</p>
                                    </td>

                                </tr>

                                <tr valign="top">
									<th scope="row"><label><?php _e('App SID', 'aspose-ppt-importer'); ?></label></th>
									<td>	
										<input type="text" size="40" name="aspose_cloud_presentation_importer_app_sid" id="aspose_cloud_presentation_importer_app_sid" value="<?php echo get_option('aspose_cloud_presentation_importer_app_sid'); ?>" />
										<span class="description"><?php _e('Aspose for Cloud App sID.', 'aspose-ppt-importer');?></span>
									</td>
								</tr>

                                <tr valign="top">
                                    <th scope="row"><label><?php _e('App key', 'aspose-ppt-importer'); ?></label></th>
                                    <td>
                                        <input type="text" size="40" name="aspose_cloud_presentation_importer_app_key" id="aspose_cloud_presentation_importer_app_key" value="<?php echo get_option('aspose_cloud_presentation_importer_app_key'); ?>" />
                                        <span class="description"><?php _e('Aspose for Cloud App Key.', 'aspose-ppt-importer');?></span>
                                    </td>
                                </tr>


								<tr valign="top">
									<th scope="row"></th>
									<td>
										<input class="button-primary" type="submit" name="Save" value="<?php _e('Save Options', 'aspose-ppt-importer'); ?>" />
										<input class="button-secondary" type="reset" name="Reset" value="<?php _e('Reset', 'aspose-ppt-importer'); ?>" />
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

// For adding button for Aspose Cloud Presentation Importer
add_action('media_buttons_context',  'add_aspose_cloud_presentation_importer_button');

function add_aspose_cloud_presentation_importer_button($context){
    //path to my icon

    $context .= '<a id="aspose_ppt_popup" title = "Aspose Cloud Presentation Importer" class="button-primary">Aspose Cloud Presentation Importer</a>';

    return $context;
}

add_action( 'admin_footer',  'aspose_ppt_add_inline_popup_content' );
function aspose_ppt_add_inline_popup_content() {
    ?>
	<style type="text/css">
        .ui-widget-overlay {
            z-index:100 !important;
        }
    </style>
    <div id="aspose_ppt_popup_container" title="Aspose Cloud Presentation Importer">
        <p>
        <?php
        if( get_option('aspose_cloud_presentation_importer_app_sid') == '' || get_option('aspose_cloud_presentation_importer_app_key') == '') { ?>
            <h3 style="color:red"> Please go to settings page and enter valid Aspose Cloud App ID & Key. </h3>
        <?php
        } else { ?>
            <div id="tabs">
                <ul>
                    <li><a href="#tabs-1">From Local</a></li>
                    <li><a href="#tabs-2">From Aspose Cloud Storage</a></li>
                </ul>
                <div id="tabs-1">
                    <table>
                        <tr>
                            <td>
                                <?php
                                $image_library_url = get_upload_iframe_src( );
                                $image_library_url = remove_query_arg( array('TB_iframe'), $image_library_url );
                                $image_library_url = add_query_arg( array( 'context' => 'Aspose-Ppt-Importer-Select-File', 'TB_iframe' => 1 ), $image_library_url );
                                ?>

                                <p>
                                    <a title="Select Presentation File" href="<?php echo esc_url( $image_library_url ); ?>" id="select-ppt-file" class="button thickbox">Select Presentation File</a>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td><input type="text" name="ppt_file_name" style="width:250px; margin-right:10px;" id="ppt_file_name" readonly value="" />  </td>
                            <td style="margin-left:10px; vertical-align: top;"> <input type="button" class="button-primary" id="insert_ppt_content" value="Insert to Editor" /> </td>
                        </tr>


                    </table>
                </div>
                <div id="tabs-2">
                    <input type="button" class="button-primary" style="position:fixed; margin-top:5px; margin-left:350px;" id="insert_aspose_ppt_content" value="Insert to Editor" />
                    <table id="aspose_cloud_ppt" style="height: 250px; width:500px !important; overflow-y: scroll;">

                    </table>


                </div>
                <div id="target"></div>
            </div>
        <?php
        } ?>
        </p>
    </div>

    <div class="modal"></div>

<?php
}

if (check_upload_aspose_ppt_context('Aspose-Ppt-Importer-Select-File')) {

    add_filter('media_upload_tabs', 'aspose_cloud_presentation_importer_image_tabs', 10, 1);
    add_filter('attachment_fields_to_edit', 'aspose_cloud_presentation_importer_action_button', 20, 2);
    add_filter('media_send_to_editor', 'aspose_cloud_presentation_importer_file_selected', 10, 3);
}

function aspose_cloud_presentation_importer_image_tabs($_default_tabs) {

    unset($_default_tabs['type_url']);
    return($_default_tabs);
}


function aspose_cloud_presentation_importer_action_button($form_fields, $post) {

    $send = "<input type='submit' class='button-primary' name='send[$post->ID]' value='" . esc_attr__( 'Use this Presentation File For Importing' ) . "' />";

    $form_fields['buttons'] = array('tr' => "\t\t<tr class='submit'><td></td><td class='savesend'>$send</td></tr>\n");
    $form_fields['context'] = array( 'input' => 'hidden', 'value' => 'Aspose-Ppt-Importer-Select-File' );
    return $form_fields;
}


function aspose_cloud_presentation_importer_file_selected($html, $send_id) {

    $file_url = wp_get_attachment_url($send_id);
    $file_url = basename($file_url);
    ?>
    <script type="text/javascript">
        /* <![CDATA[ */
        var win = window.dialogArguments || opener || parent || top;

        win.jQuery( '#ppt_file_name' ).val('<?php echo $file_url;?>');

    </script>
    <?php
    return '';
}

function add_aspose_ppt_context_to_url($url, $type) {
    //if ($type != 'image') return $url;
    if (isset($_REQUEST['context'])) {
        $url = add_query_arg('context', $_REQUEST['context'], $url);
    }
    return $url;
}


function check_upload_aspose_ppt_context($context) {
    if (isset($_REQUEST['context']) && $_REQUEST['context'] == $context) {
        add_filter('media_upload_form_url', 'add_aspose_ppt_context_to_url', 10, 2);
        return TRUE;
    }
    return FALSE;
}
