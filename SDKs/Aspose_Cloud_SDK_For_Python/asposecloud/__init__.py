__author__ = 'AssadMahmood'


class AsposeApp:
    app_sid = ''
    app_key = ''
    output_path = ''

    def __init__(self, app_sid, app_key, output_path=None):
        AsposeApp.app_sid = app_sid
        AsposeApp.app_key = app_key
        AsposeApp.output_path = output_path


class Product:
    product_uri = 'http://api.aspose.com/v1.1/'

    def __init__(self, uri):
        Product.product_uri = uri