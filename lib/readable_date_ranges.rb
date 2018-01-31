#require 'active_support'

module ReadableDateRanges
	def self.included(base)
		this_week_start  = Date.today.at_beginning_of_week.beginning_of_day
		this_week_end    = Date.today.at_end_of_week.end_of_day
		last_week_start  = 1.week.ago.at_beginning_of_week.beginning_of_day
		last_week_end    = 1.week.ago.at_end_of_week.end_of_day
		this_month_start = Date.today.at_beginning_of_month.beginning_of_day
		this_month_end   = Date.today.at_end_of_month.end_of_day
		last_month_start = 1.month.ago.at_beginning_of_month.beginning_of_day
		last_month_end   = 1.month.ago.at_end_of_month.end_of_day
		this_year_start  = Date.today.at_beginning_of_year.beginning_of_day
		this_year_end    = Date.today.at_end_of_year.end_of_day
		last_year_start  = 1.year.ago.at_beginning_of_year.beginning_of_day
		last_year_end    = 1.year.ago.at_end_of_year.end_of_day
    
		base.scope :created_this_week,  -> { base.where("created_at between ? AND ?", this_week_start, this_week_end) }
		base.scope :created_last_week,  -> { base.where("created_at between ? AND ?", last_week_start, last_week_end) }
		base.scope :created_this_month, -> { base.where("created_at between ? AND ?", this_month_start, this_month_end) }
		base.scope :created_last_month, -> { base.where("created_at between ? AND ?", last_month_start, last_month_end) }
		base.scope :created_this_year,  -> { base.where("created_at between ? AND ?", this_year_start, this_year_end) }
		base.scope :created_last_year,  -> { base.where("created_at between ? AND ?", last_year_start, last_year_end) }
		
		base.scope :updated_this_week,  -> { base.where("updated_at between ? AND ?", this_week_start, this_week_end) }
		base.scope :updated_last_week,  -> { base.where("updated_at between ? AND ?", last_week_start, last_week_end) }
		base.scope :updated_this_month, -> { base.where("updated_at between ? AND ?", this_month_start, this_month_end) }
		base.scope :updated_last_month, -> { base.where("updated_at between ? AND ?", last_month_start, last_month_end) }
		base.scope :updated_this_year,  -> { base.where("updated_at between ? AND ?", this_year_start, this_year_end) }
		base.scope :updated_last_year,  -> { base.where("updated_at between ? AND ?", last_year_start, last_year_end) }
		
		base.scope :created_or_updated_this_week,   -> { base.where("(created_at between ? AND ?) OR (updated_at between ? AND ?)", this_week_start, this_week_end, this_week_start, this_week_end) }
		base.scope :created_or_updated_last_week,   -> { base.where("(created_at between ? AND ?) OR (updated_at between ? AND ?)", last_week_start, last_week_end, last_week_start, last_week_end) }
		base.scope :created_or_updated_this_month,  -> { base.where("(created_at between ? AND ?) OR (updated_at between ? AND ?)", this_month_start, this_month_end, this_month_start, this_month_end) }
		base.scope :created_or_updated_last_month,  -> { base.where("(created_at between ? AND ?) OR (updated_at between ? AND ?)", last_month_start, last_month_end, last_month_start, last_month_end) }
		base.scope :created_or_updated_this_year,   -> { base.where("(created_at between ? AND ?) OR (updated_at between ? AND ?)", this_year_start, this_year_end, this_year_start, this_year_end) }
		base.scope :created_or_updated_last_year,   -> { base.where("(created_at between ? AND ?) OR (updated_at between ? AND ?)", last_year_start, last_year_end, last_year_start, last_year_end) }
	end
end