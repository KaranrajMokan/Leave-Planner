import { useState } from 'react';
import Calendar from 'react-calendar';
import './customDatePicker.css';

var FontAwesome = require('react-fontawesome');
function CustomDatePicker(){
    
    const [value, onChange] = useState(new Date());

    return(
        <div>
            <Calendar 
            onChange={onChange} value={value} minDate={new Date()}  view="month" 
            minDetail="month" maxDetail="month"
            showNeighboringMonth={false}
            prevLabel={<FontAwesome style={{color: '#303334'}} name="arrow-circle-left"/>}
            prev2Label={null}
            nextLabel={<FontAwesome style={{color: '#303334'}} name="arrow-circle-right"/>}
            next2Label={null}
            tileDisabled={({ date }) => date.getDay() === 0 || date.getDay() === 6}
            />
        </div>
    );
}


export default CustomDatePicker;
