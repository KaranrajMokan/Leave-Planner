import { Component } from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { BrowserRouter as Switch, Route} from "react-router-dom";
import { withRouter } from 'react-router';
import Login from './login';
import PlanningDashboard from './planning-dasboard';
import LeavesDashboard from './leaves-dashboard';
import Register from './register';
import ResetPassword from './reset-password';
import ForgotPassword from './forgot-password';
import ValidateToken from './validate-token';
import SavePassword from './save-password';

class App extends Component {

  checkForExpiry(){
    const itemStr = localStorage.getItem("studentToken")
    var isLoggedIn;
    if(!itemStr){
      isLoggedIn = false;
    }
    else{
      const item=JSON.parse(itemStr)
      const now = new Date();
      if (now.getTime() > item.expiry){
        isLoggedIn = false;
        localStorage.clear();
        this.props.history.push({
          pathname:"/",
          state:{ isTimeout:true }
        });
      }
      else{
        isLoggedIn = true;
      }
    }
    return isLoggedIn;
  };

  render() {
    this.checkForExpiry();
    return (
      <div>
          <Switch>
            <Route path="/" exact render={props=><Login {...props}/>}/>
            <Route path="/register" exact render={props=><Register {...props}/>}/>
            <Route path="/reset-password" exact render={props=><ResetPassword {...props}/>}/>
            <Route path="/forgot-password" exact render={props=><ForgotPassword {...props}/>}/>
            <Route path="/validate-token" exact render={props=><ValidateToken {...props}/>}/>
            <Route path="/save-password" exact render={props=><SavePassword {...props}/>}/>
            <Route path="/planning-dashboard" exact render={props=><PlanningDashboard {...props}/>}/>
            <Route path="/leaves-dashboard" exact render={props=><LeavesDashboard {...props}/>}/>
          </Switch> 
      </div>);
  }

}

export default withRouter(App);
