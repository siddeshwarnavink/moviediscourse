<?php

namespace App\Http\Controllers;

use App\Models\Movie;
use Illuminate\Http\Request;

class HomeController extends Controller
{
    public function view()
    {
        $movies = Movie::all();
        return view('home', ['movies' => $movies]);
    }
}