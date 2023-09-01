<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class CreateAccountRequest extends FormRequest
{
    public function authorize()
    {
        return true;
    }

    public function rules()
    {
        return [
            'email' => 'required|email:rfc,dns|unique:users,email',
            'name' => 'required',
            'password' => 'required|min:6|confirmed',
            'password_confirmation' => 'required'
        ];
    }
}
