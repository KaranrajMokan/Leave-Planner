import { Component } from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { BrowserRouter as Switch, Route} from "react-router-dom";
import { withRouter } from 'react-router';
import Login from './login';
import PlanningDashboard from './planning-dasboard';
import LeavesDashboard from './leaves-dashboard';

import Toast from './components/toast';
import checkIcon from './images/check_icon.png';
import errorIcon from './images/error_icon.png';

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
        this.props.history.push("/");
      }
      else{
        isLoggedIn = true;
      }
    }
    return isLoggedIn;
  };

  render() {
    this.checkForExpiry();
    const testList =
    {
        title: 'Success',
        description: 'This is a success toast component',
        backgroundColor: '#5cb85c',
        icon: checkIcon
      };
    return (
      <div>
          <Switch>
            <Route path="/" exact render={props=><Login {...props}/>}/>
            <Route path="/planning-dashboard" exact render={props=><PlanningDashboard {...props}/>}/>
            <Route path="/leaves-dashboard" exact render={props=><LeavesDashboard {...props}/>}/>
          </Switch> 
          {/* <Toast toast={testList}/> */}
      </div>);
  }

}

export default withRouter(App);
