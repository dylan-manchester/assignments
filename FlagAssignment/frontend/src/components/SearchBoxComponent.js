import React, { Component } from 'react';
import CheckBoxComponent from './CheckBoxComponent'

class SearchBoxComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            text: '',
            allOptions: [],
            filteredOptions: [],
            chosen: new Set([])
        }
        this.filterOptions = this.filterOptions.bind(this);
        this.selectOptionHandler = this.selectOptionHandler.bind(this);
    }
    
    filterOptions(event) {
        let value = event.target.value;
        this.setState({text: value,
                       filteredOptions: this.state.allOptions.filter(o => o.toLowerCase().startsWith(value.toLowerCase()))});
        }
    
    
    selectOptionHandler(value) {
        if (this.props.multiselect==="true") {
            this.props.addFlag(value);
            if (this.state.chosen.has(value)) {
                let new_chosen = this.state.chosen
                new_chosen.delete(value)
                this.setState({chosen: new_chosen});
            }
            else {
                this.setState({chosen: this.state.chosen.add(value)});
            }
        }
        else {
            this.setState({text: value,
                           filteredOptions: [],
                           chosen: new Set([value])});
            this.props.selectContinent(value);
        }
    }
    
    componentDidUpdate(prevProps) {
        if (this.props.updateReady) {
            this.setState({allOptions: this.props.options,
                           filteredOptions: this.props.options});
            this.props.updateComplete();
        }
    }


    render() {
        if (this.state.allOptions.length!==0) {
            return(
                <div>
                    <div className="search-box">
                        <p>{this.props.step}</p>
                        <p>{this.props.title}</p>
                        <form>
                            <input placeholder={this.props.placeholder} name="input_field" value={this.state.text} onChange={this.filterOptions}/>
                        </form>
                        <ul className="dropdown">
                            {
                                this.state.filteredOptions.map(
                                    option =>
                                    <li key={option} className="dropdown-item">
                                        <button className="dropdown-button" onClick={() => this.selectOptionHandler(option)}>
                                            <CheckBoxComponent display={this.props.multiselect} checked={this.state.chosen.has(option)} />
                                            {option}
                                        </button>
                                    </li>
                                )
                            }
                        </ul>
                    </div>
                </div>
            )
        }  
        else {
            return (
                <div>
                </div>
            )
        }
    }
}

export default SearchBoxComponent