import React, { Component } from 'react';
import SearchBoxComponent from './SearchBoxComponent'

// Backend API URL is defined by env variables
const EMPLOYEE_API_BASE_URL = process.env.NODE_ENV === 'production' ? process.env.REACT_APP_API_BASE_URL : "http://localhost:8080/flags";

class FlagAppComponent extends Component {
    constructor(props) {
        super(props)
        
        this.state = {
            continent: "",
            flags: new Set([]),
            continentOptions: [],
            continentOptionsReady: false,
            countryOptions: [],
            countryOptionsReady: false
        }
        this.changeContinent = this.changeContinent.bind(this);
        this.addFlag = this.addFlag.bind(this);
        this.continentsUpdated = this.continentsUpdated.bind(this);
        this.countriesUpdated = this.countriesUpdated.bind(this);
    }

    componentDidMount() {
        // Load options for continent search box
        this.getData("",this.getContinentHandler);
    }

    // Passable function to allow continent search box indicate that it was updated
    continentsUpdated() {
        this.setState({continentOptionsReady: false})
    }

    // Passable function to allow country search box indicate that it was updated
    countriesUpdated() {
        this.setState({countryOptionsReady: false})
    }

    // Passable function for continent search box to indicate a selection was chosen
    changeContinent(value) {
        this.setState({continent: value});
        // Load options for country search box
        this.getData("/"+value,this.getCountriesHandler);
    }

    // Passable function to allow country search box indicate a country was toggled
    addFlag(country) {
        this.getData("/"+this.state.continent+"/"+country,this.addFlagHandler)
    }

    // General data access function
    getData = (path,loadHandler) => {
            var xhr = new XMLHttpRequest();
            xhr.addEventListener('load', loadHandler)
            xhr.responseType = 'json';
            xhr.open('GET', EMPLOYEE_API_BASE_URL+path);
            xhr.send();
    }

    // Toggles display of flag received by request
    addFlagHandler = (rv) => {
        let value = rv.target.response.content[0];
        if (this.state.flags.has(value)) {
            let new_flags = this.state.flags
            new_flags.delete(value)
            this.setState({flags: new_flags});
        }
        else {
            this.setState({flags: this.state.flags.add(value)});
        }
    }

    // Sets options and ready status for continent search box
    getContinentHandler = (rv) => {
        let value = rv.target.response.content;
        this.setState({continentOptions: value,
                       continentOptionsReady: true});
    }

    // Sets options and ready status for country search box
    getCountriesHandler = (rv) => {
        let value = rv.target.response.content;
        this.setState({countryOptions: value,
                       countryOptionsReady: true});
    }

    render() {
        return(
            <div className="App">
              <header className="App-header">Flag Selector</header>
              <div className="Search-box-container">
                <SearchBoxComponent options={this.state.continentOptions}
                                    updateReady={this.state.continentOptionsReady}
                                    updateComplete={this.continentsUpdated}
                                    multiselect="false"
                                    placeholder = "Select a Continent"
                                    step = "Step 1:"
                                    title = "Select a continent"
                                    selectOption={this.changeContinent}
                                    />
                <SearchBoxComponent options={this.state.countryOptions}
                                    updateReady={this.state.countryOptionsReady}
                                    updateComplete={this.countriesUpdated}
                                    multiselect="true"
                                    placeholder = "Select a Country"
                                    step = "Step 2:"
                                    title = "Select many countries"
                                    selectOption={this.addFlag}
                                    />
              </div>
              <div className="Flag-container">
                <header className="Flag-header">Selected Flags:</header> 
                <ul className="flags">
                    {
                        Array.from(this.state.flags).map(
                            flag =>
                            <li key={flag} className="flag">
                                {flag}
                            </li>
                        )
                    }
                </ul>
              </div>
            </div>
        )
     }
}

export default FlagAppComponent