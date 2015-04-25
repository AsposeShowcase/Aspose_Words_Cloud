<?php

// Posts Sub Section Data ... ape-admin-posts-sub-section.php
$all_post_types = get_post_types();
$all_categories = get_categories();
//echo "<pre>"; print_r($all_categories);
$all_users = get_users();

// Pages Sub Section Data ... ape-admin-pages-sub-section.php
$all_pages = get_page_templates();

?>
<h1>Aspose Cloud eBook Generator</h1>
<div class="wrap">
    <form method="post" action="">
        <h2 style="background: #ccc; color: #A02; padding:10px;">Choose What to Include in eBook</h2>
        <!-- Export all posts -->
        <div class="ape-all-posts-wrap">
            <!-- All Posts sub-content -->
            <div class="ape-sub-content">
                <table>
                    <tr>
                        <th colspan="2" style="line-height: 30px">
                            <hr/>
                        </th>
                    </tr>

                    <tr>
                        <td colspan="2"><strong> Post Types: </strong></td>
                    </tr>

                    <tr>

                        <td colspan="2">
                            <?php foreach($all_post_types as $post_type_k => $post_type_v) { ?>
                                <div style="float:left; padding-right: 10px;">
                                    <input type="checkbox" name="ape-allposts-export_post_types[]" value="<?php echo $post_type_k; ?>" />
                                    <label><?php echo ucfirst($post_type_v); ?></label>
                                </div>
                            <?php } ?>
                        </td>
                    </tr>

                    <tr>
                        <th colspan="2" style="line-height: 30px">
                            <hr/>
                        </th>
                    </tr>

                    <tr>
                        <td colspan="2" style="margin-top:10px;"><strong> Categories: </strong></td>
                    </tr>
                    <tr>

                        <td colspan="2">
                            <?php foreach($all_categories as $cat_v ) { ?>
                                <div style="float:left; padding-right: 10px;">
                                    <input type="checkbox" name="ape-allposts-export_categories[]" value="<?php echo $cat_v->cat_ID; ?>" />
                                    <label><?php echo ucfirst($cat_v->name); ?></label>
                                </div>
                            <?php } ?>
                        </td>
                    </tr>

                    <tr>
                        <th colspan="2" style="line-height: 30px">
                            <hr/>
                        </th>
                    </tr>

                    <tr>
                        <td colspan="2"><strong>Authors:</strong></td>
                    </tr>
                    <tr>

                        <td colspan="2">
                            <?php foreach($all_users as $user_v ) { ?>
                                <div style="float:left; padding-right: 10px;">
                                    <input type="checkbox" name="ape-allposts-export_authors[]" value="<?php echo $user_v->data->ID; ?>" />
                                    <label><?php echo ucfirst($user_v->data->display_name); ?></label>
                                </div>
                            <?php } ?>
                        </td>
                    </tr>

                    <tr>
                        <th colspan="2" style="line-height: 30px">
                            <hr/>
                        </th>
                    </tr>

                    <tr>
                        <td colspan="2"><strong>Date Range:</strong></td>
                    </tr>
                    <tr>
                        
                        <td colspan="2">
                            <div style="float:left; padding-right: 10px;">
                                <label for="ape-allposts-export_from"> <small>Between </small> </label>
                                <input type="text" name="ape-allposts-export_from">
                                <labe for="ape-allposts-export_to"> <small> and </small> </label>
                                <input type="text" name="ape-allposts-export_to">
                            </div>
                        </td>
                    </tr>


                    <tr>
                        <th colspan="2" style="line-height: 30px">
                            <hr/>
                        </th>
                    </tr>

                </table>


            </div>
            <!-- All Posts sub-content end -->
        </div>
        <!-- Export all posts end -->
        <div style="clear: both;"></div>
        <h2 style="background: #ccc; color: #A02; padding:10px; margin-top:10px;">eBook Details</h2>
        <table>
            <tr>
                <td><label for="ebook_name"><strong>eBook Name</strong></label></td>
                <td><input type="text" style="width: 200px;" name="ebook_name" id="ebook_name" placeholder="Enter eBook Name" /> </td>

                <td><label for="author_name"><strong>Author Name</strong></label></td>
                <td><input type="text" style="width: 200px;" name="author_name" id="author_name" placeholder="Enter eBook Author Name" /> </td>

                <td><label for="ebook_title"><strong>eBook Title</strong></label></td>
                <td><input type="text" style="width: 350px;" name="ebook_title" id="ebook_title" placeholder="Enter eBook Title" /> </td>

            </tr>
            <tr>
                <td>
                    <label for="intro_page_id"><strong>Introduction Post ID</strong></label>
                    <br />
                    <br />
                    <label for="show_source_link"><strong>Show Source Link</strong></label>
                    <br />
                    <br />
                    <label for="show_post_date"><strong>Show Post Date</strong></label>
                </td>
                <td>
                    <input type="text" style="width: 200px;" name="intro_page_id" id="intro_page_id" placeholder="Enter Introduction Post ID" />
                    <br />
                    <input type="checkbox" name="show_source_link" style="margin-top:16px;" id="show_source_link" <?php ((isset($post_params['show_source_link']) && $post_params['show_source_link'] == 'yes') ? 'checked="checked"' : '') ?> value="yes" />
                    <br />
                    <input type="checkbox" name="show_post_date" style="margin-top:20px;" id="show_post_date" <?php ((isset($post_params['show_post_date']) && $post_params['show_post_date'] == 'yes') ? 'checked="checked"' : '') ?> value="yes" />

                </td>

                <th colspan="2"><label for="copy_right_details"><strong>Copy Right Page Details</strong></label> <br />
                    <textarea style="width: 100%; height: 110px;" name="copy_right_details" id="copy_right_details"><?php echo ((isset($post_params['copy_right_details']) && $post_params['copy_right_details'] != '') ? $post_params['copy_right_details'] : '<h1>[title]</h1>
<h2>[blog]</h2>
<h3>[author]</h3>
<p>© <a href="[blogurl]">[author]</a> - [year]</p>') ?></textarea>
                </th>

                <th colspan="2"><label for="colophon_details"><strong>Colophon page Details</strong></label> <br />
                    <textarea style="width: 100%; height: 110px;" name="colophon_details" id="colophon_details"><?php echo ((isset($post_params['colophon_details']) && $post_params['colophon_details'] != '') ? $post_params['colophon_details'] : '<p><br /><br /><br />printed the [today] by aspose</p><br/><br/>Total signs: [signs]<br/>Book pages: [pages]') ?></textarea>
                </th>

            </tr>
        </table>

        <div class="export-button-wrap" style="float:right;margin-top:10px;margin-right:10px;">
             <input name="ape-export_submit_button" class="button button-primary button-large" type="submit" value="Generate eBook" />
        </div>
    </form>
</div>