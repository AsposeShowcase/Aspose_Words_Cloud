<?php
function aspose_import_export_array_builder($post_ids) {
    //echo 'fahad' . $post_ids; exit;
    if(!is_array($post_ids)){
        $post_ids = array($post_ids);
    }

    foreach($post_ids as $post_id) {

        $post_data = get_post($post_id);

        $post_title = $post_data->post_title;
        $post_content = apply_filters('the_content',$post_data->post_content) ;

        $post_contents[$post_title] = $post_content;
    }


    if(is_array($post_contents)) {
        return $post_contents;
    } else {
        return false;
    }

}

function aspose_import_export_array_to_html($post_contents){

    $upload_dir = wp_upload_dir();
    $upload_path = $upload_dir['path'] . '/';
    $filename = 'output.html';

    $output_string = <<<EOD
<!DOCTYPE html>
<html>
    <head>
        <title></title>
    </head>
    <body>
EOD;

    foreach($post_contents as $post_title=>$post_content) {
        $output_string .= <<<EOD
        <h1> {$post_title} </h1>
        <p> {$post_content} </p>
        <hr />
EOD;
    }

    $output_string .= <<<EOD
</body>
</html>
EOD;

    file_put_contents($upload_path . $filename,$output_string);

    return $upload_path . $filename;
}
