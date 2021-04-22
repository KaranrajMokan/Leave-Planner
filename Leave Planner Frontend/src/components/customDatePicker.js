import { Component } from 'react';
import Calendar from 'react-calendar';
import './customDatePicker.css';
import axios from 'axios';

var FontAwesome = require('react-fontawesome');
class CustomDatePicker extends Component{
    
    componentDidMount(){
        this.onChange(new Date());
    }

    constructor(props){
        super(props);
        this.state={
            value : new Date()
        };
        this.onChange = this.onChange.bind(this);
    }

    onChange(e){
        this.setState({value:e});
        var date = new Date();
        date.setFullYear(e.getFullYear(),e.getMonth(),e.getDate());
        console.log(date);
        const details = JSON.parse(localStorage.getItem("studentToken"));
        const requestParameters = {
            rollNumber : details.rollNumber,
            token : details.token,
            date : date
        }
        axios.request({
            method:'post',
            url:'http://localhost:8080/leaves',
            data: requestParameters
        }).then(response => {
            //console.log(response.data);
            this.props.onClickLeave(response.data,e);
        }).catch(error => {
            console.log(error.response);
        });
    }

    render(){
        return(
            <div>
                <Calendar 
                selectRange={false}
                onChange={this.onChange} value={this.state.value} minDate={new Date()}  view="month" 
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
}


export default CustomDatePicker;
