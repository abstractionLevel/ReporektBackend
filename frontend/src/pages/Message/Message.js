import React from 'react'

const Message = (props) => {
    
    const message =props.location.state.message;
    const successful = props.location.state.successful;
    console.log()

    return (
        <div>
        {message && (
            <div className="form-group">
              <div
                className={ successful ? "alert alert-success" : "alert alert-danger" }
                role="alert"
              >
                {message}
              </div>
            </div>
          )}
          </div>
    )
}

export default Message;