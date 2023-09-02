<?php

namespace App\Http\Controllers;

use App\Models\Movie;
use Illuminate\Http\Request;

class MovieController extends Controller
{
    public function view(Request $request, $id)
    {
        $movie = Movie::where('id', $id)->first();
        if(!$movie) {
            abort(404);
        }
        return view('movie', ['movie' => $movie]);
    }
}