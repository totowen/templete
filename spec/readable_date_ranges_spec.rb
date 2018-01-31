require_relative 'spec_helper'

describe User do
  
  # Clear the db after each test
  after(:each) do
    User.destroy_all
  end
  
  describe 'Created' do
  
    describe '.created_this_week' do
      it 'should contain users created this week' do
      
        # User created this week
        user = User.new
        user.created_at = '2014-11-27 12:00:00' # A thursday
        user.save
      
        # User created last week
        user2 = User.new
        user2.created_at = '2014-11-20 12:00:00' # A thursday
        user2.save
      
        expect(User.created_this_week.size).to eq 1
        expect(User.created_this_week.first).to eq(user)
      end
    end
  
    describe '.created_last_week' do
      it 'should contain users created last week' do
      
        # User created this week
        user = User.new
        user.created_at = '2014-11-27 12:00:00' # A thursday
        user.save
        
        # User created last week
        user2 = User.new
        user2.created_at = '2014-11-20 12:00:00' # A thursday
        user2.save
        
        expect(User.created_last_week.size).to eq 1
        expect(User.created_last_week.first).to eq(user2)
      end
    end
  
    describe '.created_this_month' do
      it 'should contain users created this month' do
      
        # User created this week
        user = User.new
        user.created_at = '2014-11-27 12:00:00' # A thursday
        user.save
        
        # User created last week
        user2 = User.new
        user2.created_at = '2014-10-27 12:00:00' # A thursday
        user2.save
        
        expect(User.created_this_month.size).to eq 1
        expect(User.created_this_month.first).to eq(user)
      end
    end
  
    describe '.created_last_month' do
      it 'should contain users created last month' do
      
        # User created this week
        user = User.new
        user.created_at = '2014-11-27 12:00:00' # A thursday
        user.save
        
        # User created last week
        user2 = User.new
        user2.created_at = '2014-10-27 12:00:00' # A thursday
        user2.save
      
        expect(User.created_last_month.size).to eq 1
        expect(User.created_last_month.first).to eq(user2)
      end
    end
  
    describe '.created_this_year' do
      it 'should contain users created this year' do
      
        # User created this week
        user = User.new
        user.created_at = '2014-11-27 12:00:00' # A thursday
        user.save
      
        # User created last week
        user2 = User.new
        user2.created_at = '2013-10-27 12:00:00' # A thursday
        user2.save
      
        expect(User.created_this_year.size).to eq 1
        expect(User.created_this_year.first).to eq(user)
      end
    end
  
    describe '.created_last_year' do
      it 'should contain users created last year' do
      
        # User created this week
        user = User.new
        user.created_at = '2014-11-27 12:00:00' # A thursday
        user.save
        
        # User created last week
        user2 = User.new
        user2.created_at = '2013-10-27 12:00:00' # A thursday
        user2.save
      
        expect(User.created_last_year.size).to eq 1
        expect(User.created_last_year.first).to eq(user2)
      end
    end
  end
  
  
  describe 'Updated' do

    describe '.updated_this_week' do
      it 'should contain users updated this week' do
      
        # User updated this week
        user = User.new
        user.updated_at = '2014-11-27 12:00:00' # A thursday
        user.save
      
        # User updated last week
        user2 = User.new
        user2.updated_at = '2014-11-20 12:00:00' # A thursday
        user2.save
      
        expect(User.updated_this_week.size).to eq 1
        expect(User.updated_this_week.first).to eq(user)
      end
    end
  
    describe '.updated_last_week' do
      it 'should contain users updated last week' do
      
        # User updated this week
        user = User.new
        user.updated_at = '2014-11-27 12:00:00' # A thursday
        user.save
        
        # User updated last week
        user2 = User.new
        user2.updated_at = '2014-11-20 12:00:00' # A thursday
        user2.save
        
        expect(User.updated_last_week.size).to eq 1
        expect(User.updated_last_week.first).to eq(user2)
      end
    end
  
    describe '.updated_this_month' do
      it 'should contain users updated this month' do
      
        # User updated this week
        user = User.new
        user.updated_at = '2014-11-27 12:00:00' # A thursday
        user.save
        
        # User updated last week
        user2 = User.new
        user2.updated_at = '2014-10-27 12:00:00' # A thursday
        user2.save
        
        expect(User.updated_this_month.size).to eq 1
        expect(User.updated_this_month.first).to eq(user)
      end
    end
  
    describe '.updated_last_month' do
      it 'should contain users updated last month' do
      
        # User updated this week
        user = User.new
        user.updated_at = '2014-11-27 12:00:00' # A thursday
        user.save
        
        # User updated last week
        user2 = User.new
        user2.updated_at = '2014-10-27 12:00:00' # A thursday
        user2.save
      
        expect(User.updated_last_month.size).to eq 1
        expect(User.updated_last_month.first).to eq(user2)
      end
    end
  
    describe '.updated_this_year' do
      it 'should contain users updated this year' do
      
        # User updated this week
        user = User.new
        user.updated_at = '2014-11-27 12:00:00' # A thursday
        user.save
      
        # User updated last week
        user2 = User.new
        user2.updated_at = '2013-10-27 12:00:00' # A thursday
        user2.save
      
        expect(User.updated_this_year.size).to eq 1
        expect(User.updated_this_year.first).to eq(user)
      end
    end
  
    describe '.updated_last_year' do
      it 'should contain users updated last year' do
      
        # User updated this week
        user = User.new
        user.updated_at = '2014-11-27 12:00:00' # A thursday
        user.save
        
        # User updated last week
        user2 = User.new
        user2.updated_at = '2013-10-27 12:00:00' # A thursday
        user2.save
      
        expect(User.updated_last_year.size).to eq 1
        expect(User.updated_last_year.first).to eq(user2)
      end
    end
  end
  
  
  describe 'Created or Updated' do

    describe '.created_or_updated_this_week' do
      it 'should contain users created_or_updated this week' do
      
        # User created this week
        user = User.new
        user.created_at = '2014-11-27 12:00:00' # A thursday
        user.updated_at = '2014-11-27 12:00:00' # A thursday
        user.save
        
        # User updated this week
        user2 = User.new
        user2.created_at = '2014-11-26 12:00:00' # A wednesday
        user2.updated_at = '2014-11-26 12:00:00' # A wednesday
        user2.save
      
        # User created last week
        user3 = User.new
        user3.created_at = '2014-11-20 12:00:00' # A thursday
        user3.updated_at = '2014-11-20 12:00:00' # A thursday
        user3.save
        
        # User created last week
        user4 = User.new
        user4.created_at = '2014-11-19 12:00:00' # A wednesday
        user4.updated_at = '2014-11-19 12:00:00' # A wednesday
        user4.save
      
        puts User.created_or_updated_this_week.inspect
      
        expect(User.created_or_updated_this_week.size).to eq 2
        expect(User.created_or_updated_this_week).to contain_exactly(user, user2)
      end
    end
  
    describe '.created_or_updated_last_week' do
      it 'should contain users created_or_updated last week' do
      
        # User created_or_updated this week
        user = User.new
        user.created_at = '2014-11-27 12:00:00' # A thursday
        user.updated_at = '2014-11-27 12:00:00' # A thursday
        user.save
        
        # User created_or_updated last week
        user2 = User.new
        user2.created_at = '2014-11-20 12:00:00' # A thursday
        user2.updated_at = '2014-11-20 12:00:00' # A thursday
        user2.save
        
        expect(User.created_or_updated_last_week.size).to eq 1
        expect(User.created_or_updated_last_week.first).to eq(user2)
      end
    end
  
    describe '.created_or_updated_this_month' do
      it 'should contain users created_or_updated this month' do
      
        # User created_or_updated this week
        user = User.new
        user.created_at = '2014-11-27 12:00:00' # A thursday
        user.updated_at = '2014-11-27 12:00:00' # A thursday
        user.save
        
        # User created_or_updated last week
        user2 = User.new
        user2.created_at = '2014-10-27 12:00:00' # A thursday
        user2.updated_at = '2014-10-27 12:00:00' # A thursday
        user2.save
        
        expect(User.created_or_updated_this_month.size).to eq 1
        expect(User.created_or_updated_this_month.first).to eq(user)
      end
    end
  
    describe '.created_or_updated_last_month' do
      it 'should contain users created_or_updated last month' do
      
        # User created_or_updated this week
        user = User.new
        user.created_at = '2014-11-27 12:00:00' # A thursday
        user.updated_at = '2014-11-27 12:00:00' # A thursday
        user.save
        
        # User created_or_updated last week
        user2 = User.new
        user2.created_at = '2014-10-27 12:00:00' # A thursday
        user2.updated_at = '2014-10-27 12:00:00' # A thursday
        user2.save
      
        expect(User.created_or_updated_last_month.size).to eq 1
        expect(User.created_or_updated_last_month.first).to eq(user2)
      end
    end
  
    describe '.created_or_updated_this_year' do
      it 'should contain users created_or_updated this year' do
      
        # User created_or_updated this week
        user = User.new
        user.created_at = '2014-11-27 12:00:00' # A thursday
        user.updated_at = '2014-11-27 12:00:00' # A thursday
        user.save
      
        # User created_or_updated last week
        user2 = User.new
        user2.created_at = '2013-10-27 12:00:00' # A thursday
        user2.updated_at = '2013-10-27 12:00:00' # A thursday
        user2.save
      
        expect(User.created_or_updated_this_year.size).to eq 1
        expect(User.created_or_updated_this_year.first).to eq(user)
      end
    end
  
    describe '.created_or_updated_last_year' do
      it 'should contain users created_or_updated last year' do
      
        # User created_or_updated this week
        user = User.new
        user.created_at = '2014-11-27 12:00:00' # A thursday
        user.updated_at = '2014-11-27 12:00:00' # A thursday
        user.save
        
        # User created_or_updated last week
        user2 = User.new
        user2.created_at = '2013-10-27 12:00:00' # A thursday
        user2.updated_at = '2013-10-27 12:00:00' # A thursday
        user2.save
      
        expect(User.created_or_updated_last_year.size).to eq 1
        expect(User.created_or_updated_last_year.first).to eq(user2)
      end
    end
  end
end