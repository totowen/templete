require 'readable_date_ranges'
require 'active_support/all'
require 'active_record'

ActiveRecord::Base.establish_connection :adapter => 'sqlite3',
                                        :database => ':memory:'

ActiveRecord::Schema.define(version: 1) do
  create_table :users do |t|
    t.datetime :created_at
    t.datetime :updated_at
  end
end

class User < ActiveRecord::Base
  include ReadableDateRanges
end
