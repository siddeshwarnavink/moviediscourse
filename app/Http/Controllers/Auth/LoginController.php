<?php

namespace App\Http\Controllers\Auth;

use App\Models\User;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use App\Http\Requests\LoginRequest;

class LoginController extends Controller
{
    public function view()
    {
        return view('auth.login');
    }

    public function action(LoginRequest $request) 
    {
        $credentials = $request->validated();
        if(!Auth::validate($credentials)) {
            return redirect()->route('auth.login.view')->withErrors(trans('auth.failed'));
        }
        $user = Auth::getProvider()->retrieveByCredentials($credentials);
        Auth::login($user);
        $previous = $request->input('previous');
        return $previous ? redirect()->to($previous) : redirect()->intended(route('home'));
    }
}