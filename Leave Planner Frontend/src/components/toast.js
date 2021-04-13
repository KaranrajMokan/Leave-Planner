import {Component} from 'react';
import { withRouter } from 'react-router';
import './toast.css';

class Toast extends Component{

    constructor(props){
        super(props);
        this.state = {
            toast : this.props.toast,
            page : this.props.page
        };
        this.destructMe = this.destructMe.bind(this);
    }

    destructMe(){
        if (this.state.page === "login"){
            window.location.href = '/';
        }
        else if (this.state.page === "planning-dashboard"){
            window.location.href = '/planning-dashboard';
        }
        else if (this.state.page === "leaves-dashboard"){
            window.location.href = '/leaves-dashboard';
        }
        else if (this.state.page === "register"){
            window.location.href = '/register';
        }
        else if (this.state.page === "reset-password"){
            window.location.href = '/reset-password';
        }
    }

    render(){
        return(
            <div style={{ backgroundColor: this.state.toast.backgroundColor }} className="notification-container anime">
                <div className="img-division">
                    &nbsp;<img style={{ width:45, height:45}}src={this.state.toast.icon} alt=""/>
                </div>
                <button className="button-division" onClick={this.destructMe}>X</button>
                <span>
                    <p className="notification-title">{this.state.toast.title}</p>
                    <p className="notification-message">{this.state.toast.description}</p>
                </span>
            </div>
        );
    }
}

export default withRouter(Toast);