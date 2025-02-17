import { Component } from "react"
import { useParams } from "react-router-dom"
import { getUser } from "./api";
import { Alert } from "@/Shared/components/Alert";
import { withTranslation } from "react-i18next";

export class UserClass extends Component{

    state={
        user : null,
         apiProgress : false,
        error:null,

    };

//component first
    async componentDidCatch(){
      this.setState({apiProgress : true});

            try{
                const response =  await getUser(this.props.id)
                this.setState({
                    user : response.data,
                });
            } catch (axiosError) {
                    this.setState({
                    error: this.props.t('userNotFoundError'),
                    });
            }finally{
                this.setState({apiProgress :false})
            }
    }

        //component update
    componentDidUpdate(){

    }

    //component close
    componentWillUnmount(){

    }

    render(){
        return <>
        {this.state.user && <h1> {this.state.user.username} </h1>}
   {this.state.error && <Alert styleType = "danger"> {this.state.error}</Alert>}
        </>
    }
 
 
}

const UserPageWithTranslation = withTranslation()(UserClass)

export function User() {
    const{id} = useParams();


    return <UserPageWithTranslation id= {id} />
    
}