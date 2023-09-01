<?php

namespace App\Http\Controllers\Auth;

use App\Models\User;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Http\Requests\CreateAccountRequest;

class CreateAccountController extends Controller
{
    public function view()
    {
        return view('auth.createaccount');
    }

    public function action(CreateAccountRequest $request) 
    {
        $user = User::create($request->validated());
        auth()->login($user);
        return redirect('/')->with('success', 'Account created successfully.');
    }
}