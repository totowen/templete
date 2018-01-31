Gem::Specification.new do |s|
  s.name        = 'readable_date_ranges'
  s.version     = '0.1.0'
  s.date        = '2014-11-27'
  s.summary     = "Readable Range Selections"
  s.description = "Add readable range selections to Active Record Models "
  s.authors     = ["Kevin Kaske"]
  s.email       = 'kevinkaske@gmail.com'
  s.files       = ["lib/readable_date_ranges.rb"]
  s.homepage    =
    'https://github.com/kevinkaske/readable_date_ranges'
  s.license       = 'Apache 2.0'
  s.add_development_dependency 'bundler', '~> 1.3'
  s.add_development_dependency 'rake'
  s.add_development_dependency 'rspec'
  s.add_development_dependency 'activesupport'
  s.add_development_dependency 'activerecord'
  s.add_development_dependency 'sqlite3'
  s.add_development_dependency 'activerecord-nulldb-adapter'
end