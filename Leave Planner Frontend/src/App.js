import { Component } from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import logo from './images/LP-logo.png';
import Login from './login';

class App extends Component {

  render() {
      return (
      <div>
        <img className="image-div" src={logo} alt=""></img>
        <div className="line-div">
          <div className="inner-div">
              <Login/>
          </div>
        </div>
      </div>);
  }

}

export default App;
