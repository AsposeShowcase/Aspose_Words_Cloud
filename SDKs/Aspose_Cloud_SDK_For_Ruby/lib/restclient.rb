module RestClient
  def self.get(url, headers={}, &block)
    headers['x-aspose-client'] = 'RubySDK/v1.0'
    Request.execute(:method => :get, :url => url, :headers => headers, &block)
  end

  def self.post(url, payload, headers={}, &block)
    headers['x-aspose-client'] = 'RubySDK/v1.0'
    Request.execute(:method => :post, :url => url, :payload => payload, :headers => headers, &block)
  end

  def self.patch(url, payload, headers={}, &block)
    headers['x-aspose-client'] = 'RubySDK/v1.0'
    Request.execute(:method => :patch, :url => url, :payload => payload, :headers => headers, &block)
  end

  def self.put(url, payload, headers={}, &block)
    headers['x-aspose-client'] = 'RubySDK/v1.0'
    Request.execute(:method => :put, :url => url, :payload => payload, :headers => headers, &block)
  end

  def self.delete(url, headers={}, &block)
    headers['x-aspose-client'] = 'RubySDK/v1.0'
    Request.execute(:method => :delete, :url => url, :headers => headers, &block)
  end

  def self.head(url, headers={}, &block)
    headers['x-aspose-client'] = 'RubySDK/v1.0'
    Request.execute(:method => :head, :url => url, :headers => headers, &block)
  end

  def self.options(url, headers={}, &block)
    headers['x-aspose-client'] = 'RubySDK/v1.0'
    Request.execute(:method => :options, :url => url, :headers => headers, &block)
  end
end